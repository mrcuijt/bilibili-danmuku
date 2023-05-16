package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 停止的直播间信息
 */
public interface StopLiveRoomListParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.join(",",
                body.getJSONObject("data")
                        .getJSONArray("room_id_list")
                        .stream().map(f -> String.valueOf(f)).collect(Collectors.toList())));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
