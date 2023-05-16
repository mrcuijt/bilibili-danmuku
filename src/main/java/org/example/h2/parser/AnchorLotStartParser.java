package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.ANCHOR_LOT_START_TEMPLATE;

public interface AnchorLotStartParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String room_id = body.getJSONObject("data").getString("room_id");
        String danmu = body.getJSONObject("data").getString("danmu");
        Integer timestamp = body.getJSONObject("data").getInteger("current_time");
        String award_name = body.getJSONObject("data").getString("award_name");
        String award_num = body.getJSONObject("data").getString("award_num");
        String gift_num = body.getJSONObject("data").getString("gift_num");
        String require_text = body.getJSONObject("data").getString("require_text");
        String time = body.getJSONObject("data").getString("time");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(ANCHOR_LOT_START_TEMPLATE, danmukEnum().getDesc(), award_name, award_num, require_text, time, danmu));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(room_id);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
