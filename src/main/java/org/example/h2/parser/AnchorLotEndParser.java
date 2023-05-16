package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

public interface AnchorLotEndParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = ((HotRoomNotifyParser) () -> DanmukEnum.HOT_ROOM_NOTIFY).parser(body);
        danmuk.setDanmuk(danmukEnum().getDesc());
        danmuk.setDanmukType(danmukEnum().toString());
        return danmuk;
    }
}
