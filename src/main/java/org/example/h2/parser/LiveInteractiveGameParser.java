package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import java.util.Objects;

import static org.example.h2.constant.DanmukConstant.LIVE_INTERACTIVE_GAME_TEMPLATE;

public interface LiveInteractiveGameParser extends SendGiftParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("uname");
        String msg = body.getJSONObject("data").getString("msg");
        String gift_name = body.getJSONObject("data").getString("gift_name");
        String gift_num = body.getJSONObject("data").getString("gift_num");
        Integer timestamp = body.getJSONObject("data").getInteger("timestamp");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        if (Objects.nonNull(gift_num) && Integer.parseInt(gift_num) > 0) {
            danmuk.setDanmuk(String.format(LIVE_INTERACTIVE_GAME_TEMPLATE, danmukEnum().getDesc(), uname, gift_name, gift_num));
        } else {
            danmuk.setDanmuk(msg);
        }
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
