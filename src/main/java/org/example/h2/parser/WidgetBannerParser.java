package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

public interface WidgetBannerParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        Integer timestamp = body.getJSONObject("data").getInteger("timestamp");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
