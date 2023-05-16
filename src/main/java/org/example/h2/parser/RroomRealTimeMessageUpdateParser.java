package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.constant.DanmukConstant.ROOM_REAL_TIME_MESSAGE_UPDATE_TEMPLATE;

/**
 * 主播粉丝信息更新
 */
public interface RroomRealTimeMessageUpdateParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String fans = body.getJSONObject("data").get("fans").toString();
        String fans_club = body.getJSONObject("data").get("fans_club").toString();
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(ROOM_REAL_TIME_MESSAGE_UPDATE_TEMPLATE, fans, fans_club));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        return danmuk;
    }
}
