package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;
import org.example.h2.util.RegexUtil;

import static org.example.h2.constant.DanmukConstant.REGEX_NOTIFY_UNAME;
import static org.example.h2.constant.DanmukConstant.USER_TOAST_MSG_TEMPLATE;

public interface UserToastMsgParser extends SendGiftParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("username");
        String gift_name = body.getJSONObject("data").getString("role_name");
        String toast_msg = body.getJSONObject("data").getString("toast_msg");
        String num = body.getJSONObject("data").get("num").toString();
        String unit = body.getJSONObject("data").get("unit").toString();
        Integer start_time = body.getJSONObject("data").getInteger("start_time");
        String action = "续费";

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(USER_TOAST_MSG_TEMPLATE, toast_msg, action + gift_name + "x" + num + unit));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(start_time * 1000L, 0));

        if (RegexUtil.match(REGEX_NOTIFY_UNAME, danmuk.getDanmuk())) {
            danmuk.setDanmukUserName(RegexUtil.regex(REGEX_NOTIFY_UNAME, danmuk.getDanmuk(), 1));
            danmuk.setDanmuk(danmuk.getDanmuk().replace(RegexUtil.regex(REGEX_NOTIFY_UNAME, danmuk.getDanmuk(), 0), danmuk.getDanmukUserName()));
        }
        return danmuk;
    }
}
