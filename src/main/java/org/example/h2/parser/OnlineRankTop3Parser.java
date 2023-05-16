package org.example.h2.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.RegexUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.h2.constant.DanmukConstant.RANK_TOP_TEMPLATE;
import static org.example.h2.constant.DanmukConstant.REGEX_NOTIFY_UNAME;

public interface OnlineRankTop3Parser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        //TODO 用户进高能榜前三

        StringBuffer strb = new StringBuffer();

        JSONArray rankList = body.getJSONObject("data").getJSONArray("list");
        //rankList.stream().sorted(Comparator.comparing(o -> ((JSONObject)o).getString("rank"))).collect(Collectors.toList());
        List<JSONObject> rankList2 = rankList.stream().map(o -> (JSONObject) o)
                .sorted(Comparator.comparing(o -> o.getString("rank"))).collect(Collectors.toList());

        String rank = null;
        String msg = null;
        for (JSONObject json : rankList2) {
            rank = json.getString("rank");
            msg = json.getString("msg");
            if(RegexUtil.match(REGEX_NOTIFY_UNAME, msg)){
                msg = msg.replace(RegexUtil.regex(REGEX_NOTIFY_UNAME, msg, 0), RegexUtil.regex(REGEX_NOTIFY_UNAME, msg, 1));
            }
            strb.append(String.format(RANK_TOP_TEMPLATE, rank, msg));
        }
        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(strb.toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        return danmuk;
    }
}
