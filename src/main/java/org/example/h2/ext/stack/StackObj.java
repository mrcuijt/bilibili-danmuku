package org.example.h2.ext.stack;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.h2.ext.enums.Types;

@Getter
@Setter
public class StackObj {

    private String refId;

    private int ser;

    private int curLevel;

    private String curLevelName;

    private String outerFieldKey;

    private JSONObject jsonObj;

    private JSONArray jsonAry;

    public Types getCurOuterContainerType() {
        Types type = (jsonObj != null) ? Types.JSON_OBJECT : Types.JSON_ARRAY;
        return type;
    }

}
