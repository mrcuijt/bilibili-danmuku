package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.DANMU_AGGREGATION_TEMPLATE;

public interface DanmuAggregationParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String msg = body.getJSONObject("data").getString("msg");
        String aggregation_num = body.getJSONObject("data").getString("aggregation_num");
        Integer timestamp = body.getJSONObject("data").getInteger("timestamp");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(DANMU_AGGREGATION_TEMPLATE, msg, aggregation_num));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
