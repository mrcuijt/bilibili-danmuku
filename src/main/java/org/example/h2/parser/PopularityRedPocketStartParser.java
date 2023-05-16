package org.example.h2.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import java.util.stream.Collectors;

import static org.example.h2.constant.DanmukConstant.POPULARITY_RED_POCKET_START_TEMPLATE;

public interface PopularityRedPocketStartParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String sender_uid = body.getJSONObject("data").getString("sender_uid");
        String sender_name = body.getJSONObject("data").getString("sender_name");
        String danmu = body.getJSONObject("data").getString("danmu");
        Integer timestamp = body.getJSONObject("data").getInteger("start_time");

        StringBuffer strb = new StringBuffer();
        body.getJSONObject("data").getJSONArray("awards")
                .stream().map(o -> (JSONObject)o).collect(Collectors.toList()).stream().forEach(f -> {
            String num = f.getString("num");
            String gift_name = f.getString("gift_name");
            strb.append(gift_name).append("x").append(num).append(" ");
        });

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(POPULARITY_RED_POCKET_START_TEMPLATE, sender_name, danmu, strb.toString().trim()));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(sender_uid);
        danmuk.setDanmukUserName(sender_name);
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
