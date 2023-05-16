package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.constant.DanmukConstant.VOICE_JOIN_ROOM_COUNT_INFO_TEMPLATE;

public interface VoiceJoinRoomCountInfoParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String room_id = body.getJSONObject("data").getString("room_id");
        String apply_count = body.getJSONObject("data").getString("apply_count");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(VOICE_JOIN_ROOM_COUNT_INFO_TEMPLATE, apply_count));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(room_id);
        return danmuk;
    }
}
