package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import java.util.stream.Collectors;

public interface SuperChatMessageDeleteParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String roomId = body.getString("roomid");
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.join(",",
                body.getJSONObject("data")
                        .getJSONArray("ids")
                        .stream().map(f -> String.valueOf(f)).collect(Collectors.toList())));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(roomId);
        return danmuk;
    }
}
