package org.example.h2.util;

import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class BusLine {

    String basePath;

    String path;

    @Before
    public void before() {
        basePath = "";
        path = "";

        basePath = "D:/workspace-example/download-project/";
        path = "rs-20250307-2.txt";

        basePath = "D:/workspace-example/bilibili-danmuku/target/";
        path = "rs-20250310-1.txt";
        path = "rs-20250310-2.txt";
        path = "rs-20250218.txt";
        path = "rs-20250218-2.txt";
        path = "rs-20250228-1.txt";
        path = "rs-20250306-1.txt";
        path = "";
        path = "rs-20250307-1.txt";
        path = "rs-20250307-2.txt";
        path = "";
        path = "";
        path = "";
        path = "rs-20250312-4.txt";

        fillPath();
    }

    /**
     * 当前已知弹幕类型分类
     */
    @Test
    public void danmuk_cur_tps() {
        System.out.println(DanmukEnum.values().length);
        Arrays.asList(DanmukEnum.values()).stream()
                .map(f -> f.toString()).sorted()
                .map(f -> Arrays.asList(f, DanmukEnum.valueOf(f).getDesc()))
//                .forEach(this::print_o3); // DANMU_MSG	弹幕消息
                .forEach(this::print_o4); // DANMU_MSG("弹幕消息"),
    }

    /**
     * 当前已知弹幕类型分类
     */
    @Test
    public void danmuk_parse_pre_c1() {
        String line = null;
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)), "UTF8"))) {
            while ((line = br.readLine()) != null) {
                List<Danmuk> danmuks = DanmukUtil.getDanmuk(line);
                danmuks.stream().forEach(f -> {
                    if (!set.contains(f.getDanmukType())) {
                        set.add(f.getDanmukType());
                    }
                });
            }
            set.stream().forEach(this::print_o1); // DANMU_MSG
            set.stream().forEach(this::print_o2); // "DANMU_MSG",
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看新增未包含的弹幕类型
     */
    @Test
    public void danmuk_parse_c1() {
        String line = null;
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)), "UTF8"))) {
            while ((line = br.readLine()) != null) {
                List<Danmuk> danmuks = parse_o1(line, set);
//                danmuks.stream().filter(f -> DanmukEnum._UN_KNOW_CMD_NEED_AFTER_ADD.toString().equals(f.getDanmukType()))
//                        .map(f -> f.getDanmukMsg())
//                        .forEach(this::print_o1);
                danmuks.stream().filter(f -> DanmukEnum.DANMU_MSG.toString().equals(f.getDanmukType()))
                        .map(f -> DateUtil.format(f.getDanmukTime()) + "\t" + f.getDanmuk() + "\t<" + f.getDanmukUserName() + "(" + f.getDanmukUserId() + ")>")
                        .forEach(this::print_o1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹幕文件解析
     */
    @Test
    public void danmuk_parse_c2() {
        String line = null;
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)), "UTF8"))) {
            while ((line = br.readLine()) != null) {
                List<Danmuk> danmuks = parse_o1(line, set);
                danmuks.stream().filter(f -> DanmukEnum.valueOf(f.getDanmukType()) != null)
                        .map(f -> f.getDanmukMsg())
                        .forEach(this::print_o1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹幕文件解析（通用）
     *
     * @param line
     * @param ref
     * @return
     */
    public List<Danmuk> parse_o1(String line, Set<String> ref) {
        BiFunction<String, Set<String>, List<Danmuk>> fi = new BiFunction<String, Set<String>, List<Danmuk>>() {
            @Override
            public List<Danmuk> apply(String s, Set<String> strings) {
                List<Danmuk> danmuks = DanmukUtil.getDanmuk(line);
                danmuks.stream().forEach(f -> {
                    if (!ref.contains(f.getDanmukType())) {
                        ref.add(f.getDanmukType());
                    }
                });
                return danmuks;
            }
        };
        return fi.apply(line, null);
    }

    public void print_o1(Object obj) {
        System.out.println(obj);
    }

    public void print_o2(Object obj) {
        String template = "\"%s\",";
        print_o1(String.format(template, obj));
    }

    public void print_o3(List<String> obj) {
        String template = "%s\t%s";
        print_o1(String.format(template, obj.get(0), obj.get(1)));
    }

    public void print_o4(List<String> obj) {
        String template = "%s(\"%s\"),";
        print_o1(String.format(template, obj.get(0), obj.get(1)));
    }

    private void fillPath() {
        path = basePath + path;
    }
}
