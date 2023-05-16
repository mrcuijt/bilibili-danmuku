package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

public interface GuardBuyParser extends SendGiftParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("username");
        String gift_name = body.getJSONObject("data").getString("gift_name");
        String num = body.getJSONObject("data").get("num").toString();
        Integer start_time = body.getJSONObject("data").getInteger("start_time");
        String action = "开通";

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(uname + action + gift_name + "x" + num);
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(start_time * 1000L, 0));
        return danmuk;
    }
}
