package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.enums.DanmukEnum.PREPARING;

public interface PreparingParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String roomid = body.get("roomid").toString();

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(PREPARING.getDesc());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(roomid);
        return danmuk;
    }
}
