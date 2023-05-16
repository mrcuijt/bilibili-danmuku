package org.example.h2.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.h2.constant.DanmukConstant.POPULARITY_RED_POCKET_WINNER_LIST_TEMPLATE;

public interface PopularityRedPocketWinnerListParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {

        Map<String, String> awards = body.getJSONObject("data").getJSONObject("awards").getInnerMap().keySet()
                .stream().collect(Collectors.toMap(
                        o -> o,
                        o -> body.getJSONObject("data").getJSONObject("awards").getJSONObject(o).getString("award_name")));

        List<String> uList = new ArrayList<>();
        body.getJSONObject("data").getJSONArray("winner_info")
                .stream().map(o -> (JSONArray) o).collect(Collectors.toList())
                .stream().forEach(o -> {
            String uname = o.getString(1);
            String gift_id = o.getString(3);
            uList.add(String.format(POPULARITY_RED_POCKET_WINNER_LIST_TEMPLATE, uname, awards.get(gift_id)));
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
