package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

public interface OnlineRankCountParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(body.getJSONObject("data").get("count").toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
