package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

public interface PopularityRedPocketNewParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("uname");
        String action = body.getJSONObject("data").getString("action");
        String gift_name = body.getJSONObject("data").getString("gift_name");
        String num = body.getJSONObject("data").getString("num");
        Integer timestamp = body.getJSONObject("data").getInteger("start_time");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(uname + action + gift_name + "x" + num);
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
