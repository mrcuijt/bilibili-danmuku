package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

public interface HotRankSettlementV2Parser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        Danmuk danmuk = ((HotRankSettlementParser) () -> DanmukEnum.HOT_RANK_SETTLEMENT).parser(body);
        danmuk.setDanmukType(danmukEnum().toString());
        return danmuk;
    }
}
