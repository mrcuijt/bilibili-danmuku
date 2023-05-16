package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.DateUtil;
import org.example.h2.util.RegexUtil;

import static org.example.h2.constant.DanmukConstant.REGEX_NOTIFY_UNAME;

public interface EntryEffectParser extends DanmukParser {

    @Override
    default Danmuk parser(JSONObject body) {
        String timestamp19 = body.getJSONObject("data").getString("trigger_time");

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(body.getJSONObject("data").get("copy_writing").toString());
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        danmuk.setDanmukUserId(body.getJSONObject("data").get("uid").toString());
        if(RegexUtil.match(REGEX_NOTIFY_UNAME, danmuk.getDanmuk())){
            danmuk.setDanmukUserName(RegexUtil.regex(REGEX_NOTIFY_UNAME, danmuk.getDanmuk(), 1));
            danmuk.setDanmuk(danmuk.getDanmuk().replace(RegexUtil.regex(REGEX_NOTIFY_UNAME, danmuk.getDanmuk(), 0), danmuk.getDanmukUserName()));
        }
        danmuk.setDanmukTime(DateUtil.toLocalDateTime(timestamp19));
        return danmuk;
    }
}
