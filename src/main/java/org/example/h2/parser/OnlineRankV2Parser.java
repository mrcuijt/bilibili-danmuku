package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

import static org.example.h2.constant.DanmukConstant.RANK_TOP_TEMPLATE;

public interface OnlineRankV2Parser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        StringBuffer strb = new StringBuffer();
        String dataKey = body.getJSONObject("data").keySet().contains("online_list") ? "online_list" : "list";
        for(int i = 0; i < body.getJSONObject("data").getJSONArray(dataKey).size(); i++){
            JSONObject rankUser = body.getJSONObject("data").getJSONArray(dataKey).getJSONObject(i);
            String rank = rankUser.getString("rank");
            String uname = rankUser.getString("uname");
            strb.append(String.format(RANK_TOP_TEMPLATE, rank, uname));
        }

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(strb.toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
