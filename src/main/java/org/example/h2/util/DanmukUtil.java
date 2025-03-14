package org.example.h2.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.example.h2.parser.DanmukHolder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DanmukUtil {

    public static Set<String> cmd = new LinkedHashSet<>();

    public static List<String> cmds = Arrays.stream(DanmukEnum.values())
            .map(f -> f.name()).collect(Collectors.toList());

    public static String damkuPath = "";

    private static final String CHARSET = "UTF-8";

    public static List<File> getDanmukFiles(String damkuPath) {
        if (Objects.isNull(damkuPath) || damkuPath.trim().length() == 0)
            damkuPath = DanmukUtil.damkuPath;
        File filePath = new File(damkuPath);
        if (!filePath.exists() || filePath.listFiles().length == 0)
            return Collections.emptyList();
        List<File> fileList = Arrays.stream(filePath.listFiles())
                .filter(f -> f.getName().endsWith(".json")).collect(Collectors.toList());
        return fileList;
    }

    public static List<Danmuk> getDanmuk(List<File> danmukFiles) {
        return getDanmuk(danmukFiles, DanmukUtil.CHARSET);
    }

    public static List<Danmuk> getDanmuk(List<File> danmukFiles, String charset) {
        List<Danmuk> danmukList = new ArrayList<>();
        String ch = getCharset(charset);
        danmukFiles.stream().forEach(f -> danmukList.addAll(getDanmuk(f, ch)));
        return danmukList;
    }

    public static List<Danmuk> getDanmuk(File danmukFile, String charset) {
        String ch = getCharset(charset);
        List<Danmuk> danmukList = new ArrayList<>();
        try {
            String datas = FileUtils.readFileToString(danmukFile, ch);
            if (datas.startsWith("{")) {
                JSONObject jsonObject = JSON.parseObject(datas, JSONObject.class);
                if (jsonObject.getInnerMap().get("body") instanceof JSONArray) {
                    JSONArray body = jsonObject.getJSONArray("body");
                    parseDanmukMsg(body, danmukList);
                }
                //System.out.println(jsonObject.toString(SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String roomid = danmukFile.getName().split("-")[0];
        danmukList.stream().forEach(f -> {
            if (Objects.isNull(f.getRoomId()))
                f.setRoomId(roomid);
            f.setFilePath(danmukFile.getName());
        });
        return danmukList;
    }

    public static List<Danmuk> getDanmuk(String content) {
        List<Danmuk> danmukList = new ArrayList<>();
        try {
            if (content.startsWith("{")) {
                JSONObject jsonObject = JSON.parseObject(content, Feature.OrderedField);
                if (jsonObject.getInnerMap().get("body") instanceof JSONArray) {
                    JSONArray body = jsonObject.getJSONArray("body");
                    parseDanmukMsg(body, danmukList);
                }
                //System.out.println(jsonObject.toString(SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danmukList;
    }

    public static void parseDanmukMsg(JSONArray body, List<Danmuk> danmukList) {
        for (int i = 0; i < body.size(); i++) {
            if (body.get(i) instanceof JSONObject) {
                JSONObject item = body.getJSONObject(i);
                String cmd = item.getString("cmd");
                if (!DanmukUtil.cmd.contains(cmd)) {
                    DanmukUtil.cmd.add(cmd);
                }
                Danmuk danmuk = DanmukHolder.getDanmukParser(getDanmukEnum(cmd)).parser(item);
                danmukList.add(danmuk);
            } else if (body.get(i) instanceof JSONArray) {
                parseDanmukMsg(body.getJSONArray(i), danmukList);
            }
        }
    }

    public static DanmukEnum getDanmukEnum(String cmd) {
        if (cmd.startsWith(DanmukEnum.DANMU_MSG.toString())) {
            return DanmukEnum.DANMU_MSG;
        }
        if (cmds.contains(cmd)) {
            return DanmukEnum.valueOf(cmd);
        }
        return DanmukEnum._UN_KNOW_CMD_NEED_AFTER_ADD;
    }

    public static String getCharset(String charset) {
        if (Objects.isNull(charset) || charset.length() == 0)
            charset = DanmukUtil.CHARSET;
        return charset;
    }
}
