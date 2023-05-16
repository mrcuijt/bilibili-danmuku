package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

public interface VoiceJoinSwitchParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String room_id = body.getJSONObject("data").getString("room_id");
        Danmuk danmuk = ((HotRoomNotifyParser) () -> DanmukEnum.HOT_ROOM_NOTIFY).parser(body);
        danmuk.setDanmuk(danmukEnum().getDesc());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setRoomId(room_id);
        return danmuk;
    }
}
