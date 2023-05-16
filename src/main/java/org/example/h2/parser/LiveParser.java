package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.example.h2.constant.DanmukConstant.LIVE_TEMPLATE;

public interface LiveParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String roomid = body.getString("roomid");
        Integer live_time = body.getInteger("live_time");
        String live_platform = body.getString("live_platform");

        String liveTimeformat = "";
        LocalDateTime liveTime = null;
        if(Objects.nonNull(live_time)) {
            liveTime = DateUtil.toLocalDateTime(live_time * 1000L, 0);
            liveTimeformat = DateUtil.format(liveTime);
        }

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(LIVE_TEMPLATE, danmukEnum().getDesc(), live_platform.toUpperCase(), liveTimeformat));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setRoomId(roomid);
        danmuk.setDanmukTime(liveTime);
        return danmuk;
    }
}
