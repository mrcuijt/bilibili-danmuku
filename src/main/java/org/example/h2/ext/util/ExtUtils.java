package org.example.h2.ext.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.ext.dao.ExtDao;
import org.example.h2.ext.dao.ExtDaoImpl;
import org.example.h2.ext.entity.DanmukExart;
import org.example.h2.ext.entity.DanmukOrigin;
import org.example.h2.util.BrotliUtils;
import org.example.h2.util.DanmukUtil;
import org.example.h2.util.DateUtil;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

public class ExtUtils {

    public static void run(String zip, int max) throws Exception {
        String batchNo = IdWorker.getIdStr();
        ZipFile zipFile = BrotliUtils.getZipFile(zip);
        List<String> entryList = BrotliUtils.entryList(zip).stream().sorted().collect(Collectors.toList());

        ExtDao extDao = new ExtDaoImpl();
        int cur = 0;
        int maxSize = entryList.size();
        while (cur < maxSize) {
            List<String> range = new ArrayList<>();
            // limit max
            for (; cur < maxSize; cur++) {
                range.add(entryList.get(cur));
                if ((cur + 1) % max == 0) {
                    cur++;
                    break;
                }
            }

            // process
            List<DanmukOrigin> danmukOrigins = new ArrayList<>();
            for (String entry : range) {
                String line = getLine(zipFile, entry);
                if (line == null)
                    continue;
                List<Danmuk> danmuks = DanmukUtil.getDanmuk(line);
                int cur_d = 0;
                for (Danmuk danmuk : danmuks) {
                    String origin = danmuk.getDanmukMsg();
                    DanmukOrigin danmukOrigin = getDanmukOrigin(batchNo, entry, origin, cur_d);
                    danmukOrigins.add(danmukOrigin);
                    cur_d++;
                }
            }

            // batch insert
            int i = extDao.batchInsertDanmukOrigin(danmukOrigins);
            System.out.println(i);
        }

//        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(ref)))) {
//            int len = -1;
//            byte[] buff = new byte[1024];
//            for (String entry : entryList) {
//                try (InputStream inputStream = BrotliUtils.getResource(zipFile, entry);
//                     ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//                    while ((len = inputStream.read(buff, 0, buff.length)) != -1) {
//                        baos.write(buff, 0, len);
//                    }
//                    byte[] bytes = baos.toByteArray();
//                    if (bytes.length == 0)
//                        continue;
//                    String content = BrotliUtils.decompressor(bytes).toString(SerializerFeature.WriteMapNullValue);
//                    br.write(entry);
//                    br.newLine();
//                    br.write(content);
//                    br.newLine();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    public static String getLine(ZipFile zipFile, String entry) throws Exception {
        int len = -1;
        byte[] buff = new byte[1024];
        try (InputStream inputStream = BrotliUtils.getResource(zipFile, entry);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while ((len = inputStream.read(buff, 0, buff.length)) != -1) {
                baos.write(buff, 0, len);
            }
            byte[] bytes = baos.toByteArray();
            if (bytes.length == 0)
                return null;
            String content = BrotliUtils.decompressor(bytes).toString(SerializerFeature.WriteMapNullValue);
            return content;
        }
    }

    public static DanmukOrigin getDanmukOrigin(String batchNo, String fileName, String content, int ser) {
        // json obj
        JSONObject jsonObject = JSON.parseObject(content, Feature.OrderedField);
        // cmd
        String cmd = jsonObject.getString("cmd");
        // room id
        String roomId = fileName.split("-", -1)[0];
        // init
        DanmukOrigin danmukOrigin = new DanmukOrigin();
        danmukOrigin.setId(IdWorker.getIdStr());
        danmukOrigin.setBatchNo(batchNo);
        danmukOrigin.setRoomId(roomId);
        danmukOrigin.setOriginFile(fileName);
        danmukOrigin.setOriginSer(new BigDecimal(ser + ""));
        danmukOrigin.setCommand(cmd);
        danmukOrigin.setOriginContent(content);
        danmukOrigin.setCreateTime(new Date());
        return danmukOrigin;
    }

    /**
     *
     * @param jsonObj
     * @return
     */
    public static List<DanmukOrigin> getDanmukOrigin(JSONObject jsonObj) {
        return null;
    }

    /**
     *
     * @param jsonObj
     * @return
     */
    public static List<DanmukExart> getDanmukExart(JSONObject jsonObj) {
        return null;
    }
}
