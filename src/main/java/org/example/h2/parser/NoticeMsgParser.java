package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.entity.Danmuk;
import org.example.h2.util.RegexUtil;

import static org.example.h2.constant.DanmukConstant.REGEX_NOTICE_MSG;
import static org.example.h2.constant.DanmukConstant.REGEX_NOTIFY_UNAME;

/**
 * 直播间广播
 */
public interface NoticeMsgParser extends DanmukParser {

    @Override
    default Danmuk extract(Danmuk danmuk, JSONObject body) {
        danmuk.setRoomId(body.getString("real_roomid"));
        return danmuk;
    }

    @Override
    default Danmuk parser(JSONObject body) {
        String msg_common = body.getString("msg_common");
        if(RegexUtil.match(REGEX_NOTICE_MSG, msg_common)){
            String o1 = RegexUtil.regex(REGEX_NOTICE_MSG, msg_common, 1);
            String t1 = RegexUtil.regex(REGEX_NOTICE_MSG, msg_common, 2);
            String o2 = RegexUtil.regex(REGEX_NOTICE_MSG, msg_common, 3);
            String t2 = RegexUtil.regex(REGEX_NOTICE_MSG, msg_common, 4);
            msg_common = msg_common.replace(o1, t1).replace(o2, t2);
        }

        Danmuk danmuk = new Danmuk();
        danmuk.setId(IdWorker.getIdStr());
        danmuk.setDanmukType(danmukEnum().toString());
        danmuk.setDanmuk(msg_common);
        danmuk.setDanmukMsg(body.toString(SerializerFeature.WriteMapNullValue));
        extract(danmuk, body);
        return danmuk;
    }
}
