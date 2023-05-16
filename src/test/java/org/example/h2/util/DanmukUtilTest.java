package org.example.h2.util;

import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DanmukUtilTest {

    List<File> danmukFiles;

    @Before
    public void setUp(){
        danmukFiles = new ArrayList<>();
        danmukFiles.add(new File("C:/workspace-example/websocket/upload/1583810146232606722.json"));
    }

    @Test
    public void demo1(){
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(danmukFiles);
    }

    @Test
    public void demo2(){
        List<File> danmukFiles = DanmukUtil.getDanmukFiles(null);
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(danmukFiles);
        System.out.println(DanmukUtil.cmd);
    }

    @Test
    public void demo3(){
        System.out.println(DanmukEnum.values().length);
    }
}
