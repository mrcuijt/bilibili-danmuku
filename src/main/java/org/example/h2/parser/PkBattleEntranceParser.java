package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;

import java.util.Objects;

import static org.example.h2.constant.DanmukConstant.PK_BATTLE_ENTRANCE_TEMPLATE;

public interface PkBattleEntranceParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        Integer timestamp = body.getInteger("timestamp");
        Boolean is_open = body.getJSONObject("data").getBoolean("is_open");

        String status = "";
        if(Objects.isNull(is_open)){
        } else if(is_open){
            status = "开启";
        } else {
            status = "关闭";
        }

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.format(PK_BATTLE_ENTRANCE_TEMPLATE, status));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp * 1000L, 0));
        return danmuk;
    }
}
