package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import static org.example.h2.constant.DanmukConstant.INTERACT_WORD_TEMPLATE;

public interface InteractWordParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String uid = body.getJSONObject("data").getString("uid");
        String uname = body.getJSONObject("data").getString("uname");
        String roomid = body.getJSONObject("data").getString("roomid");
        String trigger_time = body.getJSONObject("data").getString("trigger_time");
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(INTERACT_WORD_TEMPLATE, uname));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(roomid);
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(trigger_time));
        return danmuk;
    }
}
