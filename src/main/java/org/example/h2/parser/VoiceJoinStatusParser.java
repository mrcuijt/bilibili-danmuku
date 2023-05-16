package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.VOICE_JOIN_STATUS_TEMPLATE;

public interface VoiceJoinStatusParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String room_id = body.getJSONObject("data").getString("room_id");
        String uid = body.getJSONObject("data").getString("uid");
        String user_name = body.getJSONObject("data").getString("user_name");
        String channel_type = body.getJSONObject("data").getString("channel_type");
        Integer timestamp = body.getJSONObject("data").getInteger("current_time");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(VOICE_JOIN_STATUS_TEMPLATE, user_name, danmukEnum().getDesc(), channel_type.toUpperCase()));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(room_id);
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(user_name);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
