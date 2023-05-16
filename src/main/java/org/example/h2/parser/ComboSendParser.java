package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

public interface ComboSendParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("uname");
        String action = body.getJSONObject("data").getString("action");
        String giftName = body.getJSONObject("data").getString("gift_name");
        String num = body.getJSONObject("data").get("gift_num").toString();
        Integer combo_num = body.getJSONObject("data").getInteger("combo_num");
        String timestamp19 = body.getJSONObject("data").getString("tid");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        if (combo_num > 0) {
            danmuk.setDanmuk(uname + action + giftName + "x" + num + " Combo X" + combo_num);
        } else {
            danmuk.setDanmuk(uname + action + giftName + "x" + num);
        }
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        return danmuk;
    }

}
