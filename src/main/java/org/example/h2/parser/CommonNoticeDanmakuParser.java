package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;

public interface CommonNoticeDanmakuParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String text = null;
        if (body.getJSONObject("data").getJSONArray("content_segments")
                .stream().map(o -> (JSONObject) o).filter(f -> f.containsKey("text")).findFirst().isPresent()) {
            text = body.getJSONObject("data").getJSONArray("content_segments")
                    .stream().map(o -> (JSONObject) o).filter(f -> f.containsKey("text")).findFirst().get().getString("text");
        }

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(text);
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        return danmuk;
    }
}
