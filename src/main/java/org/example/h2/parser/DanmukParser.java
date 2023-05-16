package org.example.h2.parser;

import com.alibaba.fastjson.JSONObject;
import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;

public interface DanmukParser {

    default Danmuk extract(Danmuk danmuk, JSONObject body) {
        return danmuk;
    }

    default Danmuk parser(JSONObject body) {
        return null;
    }

    DanmukEnum danmukEnum();
}
