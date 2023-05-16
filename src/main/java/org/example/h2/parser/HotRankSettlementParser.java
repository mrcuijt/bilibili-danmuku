package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;
import org.example.h2.util.RegexUtil;

import static org.example.h2.constant.DanmukConstant.HOT_RANK_SETTLEMENT_TEMPLATE;
import static org.example.h2.constant.DanmukConstant.REGEX_NOTIFY_UNAME;

public interface HotRankSettlementParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String area_name = body.getJSONObject("data").getString("area_name");
        String uname = body.getJSONObject("data").getString("uname");
        String dm_msg = body.getJSONObject("data").getString("dm_msg");
        String rank = body.getJSONObject("data").getString("rank");
        Integer timestamp = body.getJSONObject("data").getInteger("timestamp");
        if(RegexUtil.match(REGEX_NOTIFY_UNAME, dm_msg)){
            dm_msg = dm_msg.replace(RegexUtil.regex(REGEX_NOTIFY_UNAME, dm_msg, 0), uname);
        }
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(HOT_RANK_SETTLEMENT_TEMPLATE, area_name, rank, dm_msg));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }

}
