package org.example.h2.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.h2.constant.DanmukConstant.ANCHOR_LOT_AWARD_TEMPLATE;

public interface AnchorLotAwardParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        String award_name = body.getJSONObject("data").getString("award_name");

        List<String> uList = new ArrayList<>();
        body.getJSONObject("data").getJSONArray("award_users")
                .stream().map(o -> (JSONObject) o).collect(Collectors.toList())
                .stream().forEach(o -> {
            String uname = o.getString("uname");
            String num = o.getString("num");
            uList.add(String.format(ANCHOR_LOT_AWARD_TEMPLATE, uname, award_name, num));
        });

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(String.join(",", uList));
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
