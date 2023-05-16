package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.example.h2.util.DateUtil;

import java.util.Objects;

import static org.example.h2.constant.DanmukConstant.SUPER_CHAT_MESSAGE_TEMPLATE;

public interface SuperChatMessageJpnParser extends SendGiftParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String message = body.getJSONObject("data").getString("message");
        String message_jpn = body.getJSONObject("data").getString("message_jpn");
        Integer start_time = body.getJSONObject("data").getInteger("start_time");
        Integer end_time = body.getJSONObject("data").getInteger("end_time");
        int time = end_time - start_time;
        String superChatMessageTime = DateUtil.getSuperChatMessageTime(time);
        Danmuk danmuk = ((SuperChatMessageParser) () -> DanmukEnum.SUPER_CHAT_MESSAGE).parser(body);
        danmuk.setDanmukType(danmukEnum().toString());
        if(Objects.nonNull(message_jpn) && message_jpn.trim().length() > 0) {
            danmuk.setDanmuk(String.format(SUPER_CHAT_MESSAGE_TEMPLATE, superChatMessageTime, message));
        }
        return danmuk;
    }
}
