package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.constant.DanmukConstant.RING_STATUS_CHANGE_TEMPLATE;

public interface RingStatusChangeParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String status = body.getJSONObject("data").getString("status");
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(RING_STATUS_CHANGE_TEMPLATE, danmukEnum().getDesc(), status));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        return danmuk;
    }
}
