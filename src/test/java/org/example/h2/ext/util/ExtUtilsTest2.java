package org.example.h2.ext.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.example.h2.ext.dao.ExtDao;
import org.example.h2.ext.dao.ExtDaoImpl;
import org.example.h2.ext.entity.DanmukExart;
import org.example.h2.ext.enums.Types;
import org.example.h2.ext.stack.StackObj;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class ExtUtilsTest2 {

    String zip;

    int max;

    @Before
    public void setUp() {
        zip = "D:/tools/android/27183290-20250406.zip";
        max = 10;
    }

    @Test
    public void run() throws Exception {
        ExtUtils.run(zip, max);
    }

    @Test
    public void run2() throws Exception {
        List<DanmukExart> danmukExarts = new ArrayList<>();
        String demo = "{\"cmd\":\"DANMU_MSG\",\"dm_v2\":\"\",\"info\":[[0,1,25,5816798,1743941196824,1743941197,0,\"67220a61\",0,0,0,\"\",0,\"{}\",\"{}\",{\"extra\":\"{\\\"send_from_me\\\":false,\\\"master_player_hidden\\\":false,\\\"mode\\\":0,\\\"color\\\":5816798,\\\"dm_type\\\":0,\\\"font_size\\\":25,\\\"player_mode\\\":1,\\\"show_player_type\\\":0,\\\"content\\\":\\\"（￣▽￣）\\\",\\\"user_hash\\\":\\\"1730284129\\\",\\\"emoticon_unique\\\":\\\"\\\",\\\"bulge_display\\\":0,\\\"recommend_score\\\":9,\\\"main_state_dm_color\\\":\\\"\\\",\\\"objective_state_dm_color\\\":\\\"\\\",\\\"direction\\\":0,\\\"pk_direction\\\":0,\\\"quartet_direction\\\":0,\\\"anniversary_crowd\\\":0,\\\"yeah_space_type\\\":\\\"\\\",\\\"yeah_space_url\\\":\\\"\\\",\\\"jump_to_url\\\":\\\"\\\",\\\"space_type\\\":\\\"\\\",\\\"space_url\\\":\\\"\\\",\\\"animation\\\":{},\\\"emots\\\":null,\\\"is_audited\\\":false,\\\"id_str\\\":\\\"6c88447c88997ed248dc8a74fd67f26e8950\\\",\\\"icon\\\":null,\\\"show_reply\\\":true,\\\"reply_mid\\\":0,\\\"reply_uname\\\":\\\"\\\",\\\"reply_uname_color\\\":\\\"\\\",\\\"reply_is_mystery\\\":false,\\\"reply_type_enum\\\":0,\\\"hit_combo\\\":0,\\\"esports_jump_url\\\":\\\"\\\"}\",\"mode\":0,\"show_player_type\":0,\"user\":{\"base\":{\"face\":\"https://i0.hdslb.com/bfs/face/6aab6a92c8eed705d66823f730c953ab9044e218.jpg\",\"is_mystery\":false,\"name\":\"Mr_-_-Young\",\"name_color\":0,\"name_color_str\":\"\",\"official_info\":{\"desc\":\"\",\"role\":0,\"title\":\"\",\"type\":-1},\"origin_info\":{\"face\":\"https://i0.hdslb.com/bfs/face/6aab6a92c8eed705d66823f730c953ab9044e218.jpg\",\"name\":\"Mr_-_-Young\"},\"risk_ctrl_info\":null},\"guard\":null,\"guard_leader\":{\"is_guard_leader\":false},\"medal\":{\"color\":13081892,\"color_border\":12632256,\"color_end\":12632256,\"color_start\":12632256,\"guard_icon\":\"\",\"guard_level\":0,\"honor_icon\":\"\",\"id\":1007966,\"is_light\":0,\"level\":19,\"name\":\"雪撬犬\",\"ruid\":3493139945884106,\"score\":502900,\"typ\":0,\"user_receive_count\":0,\"v2_medal_color_border\":\"#919298CC\",\"v2_medal_color_end\":\"#919298CC\",\"v2_medal_color_level\":\"#6C6C7299\",\"v2_medal_color_start\":\"#919298CC\",\"v2_medal_color_text\":\"#FFFFFFFF\"},\"title\":{\"old_title_css_id\":\"\",\"title_css_id\":\"\"},\"uhead_frame\":null,\"uid\":380280207,\"wealth\":null}},{\"activity_identity\":\"\",\"activity_source\":0,\"not_show\":0},0],\"（￣▽￣）\",[380280207,\"Mr_-_-Young\",0,0,0,10000,1,\"\"],[19,\"雪撬犬\",\"雪糕cheese\",27183290,13081892,\"\",0,12632256,12632256,12632256,0,0,3493139945884106],[10,0,9868950,\">50000\",0],[\"\",\"\"],0,0,null,{\"ct\":\"65D45B83\",\"ts\":1743941196},0,0,null,null,0,130,[4],null]}";
        JSONObject jsonObj = JSON.parseObject(demo, Feature.OrderedField);
        Set<String> keySet = jsonObj.keySet();
        Deque<StackObj> stack = new LinkedList<>();
        String cur_level_name = "$";
        String ref = "001";
        int cur_ser = 0;
        int cur_level = 0;
        for (String key : keySet) {
            Object obj = jsonObj.get(key);
            DanmukExart exart = getExart(stack, ref, key, obj, cur_ser, cur_level, cur_level_name, Types.JSON_OBJECT);
            danmukExarts.add(exart);
            cur_ser++;
        }
        processChild(danmukExarts, stack);
        ExtDao extDao = new ExtDaoImpl();
        int i = extDao.batchInsertDanmukExart(danmukExarts);
        System.out.println(i);
    }

    public static DanmukExart getExart(Deque<StackObj> stack, String ref, String key, Object obj, int ser, int level, String levelName, Types outerType) {
        DanmukExart danmukExart = new DanmukExart();
        danmukExart.setId(IdWorker.getIdStr());
        danmukExart.setRefId(ref);
        danmukExart.setSpecFiled(key);
        danmukExart.setSpecValType(Types.UN_KNOW.name());
        danmukExart.setSpecValSer(new BigDecimal(ser + ""));
        danmukExart.setSpecLevel(new BigDecimal(level + ""));
        danmukExart.setSpecLevelName(levelName);
        danmukExart.setSpecOuterContainerType(outerType.name());
        danmukExart.setCreateTime(new Date());
        if (obj == null) {
            return danmukExart;
        }

        Types type = getTypes(obj);
        switch (type) {
            case JSON_OBJECT:
            case JSON_ARRAY:
                danmukExart.setSpecValType(type.name());
                StackObj stackObj = getStackObj(ref, obj, level + 1, (type.equals(Types.JSON_ARRAY)) ? key + " item " + ser : key, ser);
                stack.add(stackObj);
                break;
            default:
                danmukExart.setSpecVal(String.valueOf(obj));
                danmukExart.setSpecValType(type.name());
                break;
        }
        return danmukExart;
    }

    public static StackObj getStackObj(String refId, Object obj, int level, String levelName, int ser) {
        StackObj item = new StackObj();
        item.setRefId(refId);
        item.setSer(ser);
        item.setCurLevel(level);
        item.setCurLevelName(levelName);
        if (obj instanceof JSONObject) {
            item.setJsonObj((JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            item.setJsonAry((JSONArray) obj);
        }
        return item;
    }

    public static Types getTypes(Object obj) {
        Types type = null;
        if (obj instanceof JSONObject) {
            type = Types.JSON_OBJECT;
        } else if (obj instanceof JSONArray) {
            type = Types.JSON_ARRAY;
        } else if (obj instanceof String) {
            type = Types.STRING;
        } else if (obj instanceof Boolean) {
            type = Types.BOOLEAN;
        } else if (obj instanceof BigDecimal) {
            type = Types.BIG_DECIMAL;
        } else if (obj instanceof BigInteger) {
            type = Types.BIG_INTEGER;
        } else if (obj instanceof Float) {
            type = Types.FLOAT;
        } else if (obj instanceof Double) {
            type = Types.DOUBLE;
        } else if (obj instanceof Byte) {
            type = Types.BYTE;
        } else if (obj instanceof Short) {
            type = Types.SHORT;
        } else if (obj instanceof Integer) {
            type = Types.INTEGER;
        } else if (obj instanceof Long) {
            type = Types.LONG;
        } else if (obj instanceof Date) {
            type = Types.DATE;
        } else if (obj instanceof Timestamp) {
            type = Types.TIMESTAMP;
        }
        return (type == null) ? Types.UN_KNOW : type;
    }

    public static void processChild(List<DanmukExart> danmukExarts, Deque<StackObj> stack) {
        while (!stack.isEmpty()) {
            StackObj child = stack.pop();
            String ref = child.getRefId();
            int ser = child.getSer();
            int cur_level = child.getCurLevel();
            String cur_level_name = child.getCurLevelName();
            Types outerType = child.getCurOuterContainerType();
            int cur_ser = 0;
            switch (outerType) {
                case JSON_OBJECT:
                    JSONObject jsonObj = child.getJsonObj();
                    Set<String> keySet = jsonObj.keySet();
                    for (String key : keySet) {
                        Object obj = jsonObj.get(key);
                        DanmukExart exart = getExart(stack, ref, key, obj, cur_ser, cur_level, cur_level_name, outerType);
                        danmukExarts.add(exart);
                        cur_ser++;
                    }
                    break;
                case JSON_ARRAY:
                    JSONArray jsonAry = child.getJsonAry();
                    for (int i = 0; i < jsonAry.size(); i++) {
                        Object obj = jsonAry.get(i);
                        DanmukExart exart = getExart(stack, ref, cur_level_name + " item " + ser, obj, cur_ser, cur_level, cur_level_name, outerType);
                        danmukExarts.add(exart);
                        cur_ser++;
                    }
                    break;
            }
        }
    }
}
