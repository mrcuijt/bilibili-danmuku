package org.example.h2.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.brotli.dec.BrotliInputStream;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class BrotliUtils {

    public static class WsConstants {
        public static final int WS_AUTH_OK = 0;
        public static final int WS_AUTH_TOKEN_ERROR = -101;
        /**
         * WebSocket 数据包协议版本（BROTLI）
         */
        public static final int WS_BODY_PROTOCOL_VERSION_BROTLI = 3;
        /**
         * WebSocket 数据包协议版本（NORMAL）
         */
        public static final int WS_BODY_PROTOCOL_VERSION_NORMAL = 0;
        public static final int WS_HEADER_DEFAULT_OPERATION = 1;
        public static final int WS_HEADER_DEFAULT_SEQUENCE = 1;
        public static final int WS_HEADER_DEFAULT_VERSION = 1;
        public static final int WS_HEADER_OFFSET = 4;
        public static final int WS_OPERATION_OFFSET = 8;
        public static final int WS_OP_CONNECT_SUCCESS = 8;
        public static final int WS_OP_HEARTBEAT = 2;
        /**
         * WebSocket 心跳回复
         */
        public static final int WS_OP_HEARTBEAT_REPLY = 3;
        public static final int WS_OP_MESSAGE = 5;
        public static final int WS_OP_USER_AUTHENTICATION = 7;
        /**
         * WebSocket 数据包头信息总长度（字节）
         */
        public static final int WS_PACKAGE_HEADER_TOTAL_LENGTH = 16;
        /**
         * WebSocket 数据包偏移量
         */
        public static final int WS_PACKAGE_OFFSET = 0;
        public static final int WS_SEQUENCE_OFFSET = 12;
        public static final int WS_VERSION_OFFSET = 6;

        /**
         * WebSocket Header Length 数据包头信息长度 {字节数, 字节偏移量, 值}
         */
        public static final int[] headerLen = {2, 4, 16};
        /**
         * WebSocket Protocol Version 数据包协议版本 {字节数, 字节偏移量, 值}
         */
        public static final int[] ver = {2, 6, 1};
        /**
         * WebSocket Operation 数据包操作 {字节数, 字节偏移量, 值}
         */
        public static final int[] op = {4, 8, 2};
        /**
         * WebSocket Sequence Id 数据包序列标识ID {字节数, 字节偏移量, 值}
         */
        public static final int[] seq = {4, 12, 1};
    }

    public static enum WsEnum {

        PACKET_LEN("packetLen", "数据包长度（整体）", new int[]{0, 0, 1}),
        HEADER_LEN("headerLen", "头信息长度", new int[]{2, 4, 16}),
        OP("op", "", new int[]{4, 8, 2}),
        SEQ("seq", "", new int[]{4, 12, 1}),
        VER("ver", "数据包协议版本", new int[]{2, 6, 1}),
        ;

        String code;
        String desc;
        int[] range;

        WsEnum(String code, String desc, int[] range) {
            this.code = code;
            this.desc = desc;
            this.range = range;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public int[] getRange() {
            return range;
        }
    }

    public static JSONObject initPacket() {
        JSONObject packet = new JSONObject(true);
        packet.put("body", "");
        initPacketVal(packet, WsEnum.PACKET_LEN);
        initPacketVal(packet, WsEnum.HEADER_LEN);
        initPacketVal(packet, WsEnum.VER);
        initPacketVal(packet, WsEnum.OP);
        initPacketVal(packet, WsEnum.SEQ);
        return packet;
    }

    public static void initPacketVal(JSONObject packet, WsEnum wsEnum) {
        setPacketVal(packet, wsEnum, 0);
    }

    public static void setPacketVal(JSONObject packet, WsEnum wsEnum, int val) {
        packet.put(wsEnum.getCode(), val);
    }

    public static int getPacketVal(JSONObject packet, WsEnum wsEnum) {
        return packet.getInteger(wsEnum.getCode());
    }

    public static JSONObject decompressor(byte[] datas) throws Exception {
        JSONObject packet = initPacket();
        setPacketVal(packet, WsEnum.PACKET_LEN, getInt(getUint32(datas, WsConstants.WS_PACKAGE_OFFSET)));
        setPacketVal(packet, WsEnum.HEADER_LEN, getInt(getUint16(datas, WsEnum.HEADER_LEN.getRange()[1])));
        setPacketVal(packet, WsEnum.OP, getInt(getUint32(datas, WsEnum.OP.getRange()[1])));
        setPacketVal(packet, WsEnum.SEQ, getInt(getUint32(datas, WsEnum.SEQ.getRange()[1])));
        setPacketVal(packet, WsEnum.VER, getInt(getUint16(datas, WsEnum.VER.getRange()[1])));
        // 数据包异常跳过
        if (getPacketVal(packet, WsEnum.PACKET_LEN) < 0
                || getPacketVal(packet, WsEnum.PACKET_LEN) > datas.length) {
            System.out.println("Packet Len Not Match:" + getPacketVal(packet, WsEnum.PACKET_LEN) + "/" + datas.length);
            return packet;
            //if (getPacketVal(packet, WsEnum.PACKET_LEN) - 2 == datas.length) {
            //    byte[] miss = new byte[datas.length + 2];
            //    System.arraycopy(datas, 0, miss, 0, datas.length);
            //    return decompressor(miss);
            //}
            //return packet;
        }
        // 心跳回复 OP:3 VER:1
        if (getPacketVal(packet, WsEnum.OP) == WsConstants.WS_OP_HEARTBEAT_REPLY) {
            JSONObject item = new JSONObject();
            item.put("count", getInt(getUint32(datas, WsConstants.WS_PACKAGE_HEADER_TOTAL_LENGTH)));
            packet.put("body", item);
            return packet;
        } else {
            List body = new ArrayList();
            List items = new ArrayList();
            packet.put("body", body);
            int a = 0;
            int u = 0;
            int i = WsConstants.WS_PACKAGE_OFFSET;
            int s = getPacketVal(packet, WsEnum.PACKET_LEN);
            for (; i < datas.length; i += s) {
                s = getInt(getUint32(datas, i));
                a = getInt(getUint16(datas, i + WsConstants.WS_HEADER_OFFSET));
                try {
                    if (getPacketVal(packet, WsEnum.VER) == WsConstants.WS_BODY_PROTOCOL_VERSION_NORMAL) {
                        byte[] compress = Arrays.copyOfRange(datas, i + a, i + s);
                        JSONObject item = JSON.parseObject(new String(compress, "UTF8"), Feature.OrderedField);
                        body.add(item);
                    } else if (getPacketVal(packet, WsEnum.VER) == WsConstants.WS_BODY_PROTOCOL_VERSION_BROTLI) {
                        byte[] compress = Arrays.copyOfRange(datas, i + a, i + s);
                        byte[] decompress = decompress(compress);
                        JSONObject n = decompressor(decompress);
                        body.add(items);
                        items.addAll((List) n.get("body"));
                    }
                } catch (Exception e) {
                    System.out.println("decode body error:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return packet;
    }

    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    //小端模式java int转byte4byte
    public static byte[] intToByte4L(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    //小端模式java int转byte  2byte
    public static byte[] intToByte2L(int n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    //java大端模式int转byte  4byte
    public static byte[] intToByte4B(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    //java大端模式int转byte  2byte
    public static byte[] intToByte2B(int n) {
        byte[] b = new byte[2];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        return b;
    }

    //JAVA大端模式byte转int 4byte
    public static int byte4IntB(byte[] b) {
        int intValue = 0;
        byte[] t_byte = {0, 0, 0, 0};
        if (b.length <= 4) {
            System.arraycopy(b, 0, t_byte, 4 - b.length, b.length);
        }
        for (int i = 0; i < t_byte.length; i++) {
            intValue += (t_byte[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }

    //JAVA大端模式byte转int 2byte
    public static int byte2IntB(byte[] b) {
        int intValue = 0;
        byte[] t_byte = {0, 0};
        if (b.length <= 2) {
            System.arraycopy(b, 0, t_byte, 2 - b.length, b.length);
        }
        for (int i = 0; i < t_byte.length; i++) {
            intValue += (t_byte[i] & 0xFF) << (8 * (1 - i));
        }
        return intValue;
    }

    public static byte[] getUint16(byte[] datas, int offset) {
        return Arrays.copyOfRange(datas, offset, offset + 2);
    }

    public static byte[] getUint32(byte[] datas, int offset) {
        return Arrays.copyOfRange(datas, offset, offset + 4);
    }

    public static int getInt(byte[] datas) {
        return byte4IntB(datas);
    }

    public static byte[] decompress(byte[] datas) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is = new ByteArrayInputStream(datas);
             BrotliInputStream des = new BrotliInputStream(is);) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = des.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static void dataPrint(byte[] datas) {
        for (int i = 0; i < datas.length; i++) {
            System.out.print(Integer.toHexString(Byte.toUnsignedInt(datas[i])));
            System.out.print(",");
            if ((i + 1) % 16 == 0) {
                System.out.println();
            }
        }
    }

    public static Iterator<ZipEntry> iterator(ZipFile zip) {
        Iterator<ZipEntry> iter = null;
        try {
            iter = (Iterator<ZipEntry>) zip.entries();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iter;
    }

    public static Enumeration<ZipEntry> entries(ZipFile zip) {
        Enumeration<ZipEntry> entries = null;
        try {
            entries = (Enumeration<ZipEntry>) zip.entries();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<String> entryList(String path) {
        ZipFile zip = null;
        List<String> entries = new ArrayList<String>();
        try {
            zip = getZipFile(path);
            Iterator<ZipEntry> iter = iterator(zip);
            if (iter == null) return Collections.emptyList();
            while (iter.hasNext()) {
                ZipEntry entry = iter.next();
                entries.add(entry.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zip != null) zip.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // TODO: getZipFile(path) while throw Exception.
        return (entries.size() == 0) ? Collections.emptyList() : entries;
    }

    public static InputStream getResource(ZipFile zip, String entry) {
        InputStream in = new ByteArrayInputStream(new byte[]{});
        try {
            ZipEntry zipEntry = zip.getEntry(entry);
            in = zip.getInputStream(zipEntry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    public static boolean verify(String path) {
        List<String> entries = entryList(path);
        return (!entries.isEmpty());
    }

    public static ZipFile getZipFile(String path) throws ZipException, IOException {
        return new ZipFile(new File(path));
    }
}
