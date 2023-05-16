package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.constant.DanmukConstant.ROOM_CHANGE_TEMPLATE;

public interface RoomChangeParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String area_name = body.getJSONObject("data").get("area_name").toString();
        String parent_area_name = body.getJSONObject("data").get("parent_area_name").toString();
        String title = body.getJSONObject("data").get("title").toString();

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(ROOM_CHANGE_TEMPLATE, area_name, parent_area_name, title));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        return danmuk;
    }
}
