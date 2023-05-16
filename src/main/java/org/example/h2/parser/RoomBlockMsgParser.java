package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

public interface RoomBlockMsgParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String uid = body.getString("uid");
        String uname = body.getString("uname");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(danmukEnum().getDesc());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(uid);
        danmuk.setDanmukUserName(uname);
        return danmuk;
    }
}
