package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

import static org.example.h2.constant.DanmukConstant.RING_STATUS_CHANGE_TEMPLATE;

public interface RingStatusChangeV2Parser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = ((RingStatusChangeParser) () -> DanmukEnum.RING_STATUS_CHANGE).parser(body);
        danmuk.setDanmukType(danmukEnum().toString());
        return danmuk;
    }
}
