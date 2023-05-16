package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.HOT_RANK_CHANGED_TEMPLATE;

public interface HotRankChangedParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String rank = body.getJSONObject("data").get("rank").toString();
        String countdown = body.getJSONObject("data").get("countdown").toString();
        String area_name = body.getJSONObject("data").get("area_name").toString();
        Integer timestamp = body.getJSONObject("data").getInteger("timestamp");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(HOT_RANK_CHANGED_TEMPLATE, area_name, rank, area_name, countdown));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }

}
