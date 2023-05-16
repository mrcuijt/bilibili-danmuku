package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

/**
 * 弹幕内容
 */
public interface DanmuMsgParser extends DanmukParser {

    @Override
    default Danmuk extract(Danmuk danmuk, JSONObject body) {
        /*
        //TODO 弹幕表情&UP主大表情（粉丝团）
        String roomId = null;
        Object exartData = body.getJSONArray("info").getJSONArray(0).get(13);
        // 弹幕表情&UP主大表情（粉丝团）
        if(exartData instanceof JSONObject){
            String emoticon_unique = body.getJSONArray("info").getJSONArray(0).getJSONObject(13).getString("emoticon_unique");
            roomId = emoticon_unique.split("_")[1];
        }
        danmuk.setRoomId(roomId);
        */
        Long timestamp13 = body.getJSONArray("info").getJSONArray(0).getLong(4);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp13, 0));
        return null;
    }

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(body.getJSONArray("info").get(1).toString());
        danmuk.setDanmukUserId(body.getJSONArray("info").getJSONArray(2).get(0).toString());
        danmuk.setDanmukUserName(body.getJSONArray("info").getJSONArray(2).get(1).toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
