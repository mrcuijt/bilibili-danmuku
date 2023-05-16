package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.SUPER_CHAT_MESSAGE_TEMPLATE;

public interface SuperChatMessageParser extends SendGiftParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getJSONObject("user_info").getString("uname");
        String message = body.getJSONObject("data").getString("message");
        Integer ts = body.getJSONObject("data").getInteger("ts");
        Integer start_time = body.getJSONObject("data").getInteger("start_time");
        Integer end_time = body.getJSONObject("data").getInteger("end_time");
        int time = end_time - start_time;
        String superChatMessageTime = DateUtil.getSuperChatMessageTime(time);

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(SUPER_CHAT_MESSAGE_TEMPLATE, superChatMessageTime, message));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(ts * 1000L, 0));
        return danmuk;
    }
}
