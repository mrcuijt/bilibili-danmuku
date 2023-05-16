package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import java.util.Date;

/**
 * 赠送礼物
 */
public interface SendGiftParser extends DanmukParser {
    @Override
    default Danmuk parser(JSONObject body) {

        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("uname");
        String action = body.getJSONObject("data").getString("action");
        String giftName = body.getJSONObject("data").getString("giftName");
        String num = body.getJSONObject("data").get("num").toString();
        Integer super_batch_gift_num = body.getJSONObject("data").getInteger("super_batch_gift_num");
        String timestamp19 = body.getJSONObject("data").getString("tid");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        if (super_batch_gift_num > 0) {
            danmuk.setDanmuk(uname + action + giftName + "x" + num + " Combo X" + super_batch_gift_num);
        } else {
            danmuk.setDanmuk(uname + action + giftName + "x" + num);
        }
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp19));
        return danmuk;
    }
}
