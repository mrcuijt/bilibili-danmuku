package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

public interface VoiceJoinListParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = ((VoiceJoinRoomCountInfoParser) () -> DanmukEnum.VOICE_JOIN_ROOM_COUNT_INFO).parser(body);
        danmuk.setDanmukType(danmukEnum().toString());
        return danmuk;
    }
}
