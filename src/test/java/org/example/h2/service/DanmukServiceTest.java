package org.example.h2.service;

import org.example.h2.util.DanmukUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DanmukServiceTest {

    DanmukService danmukService;

    String basePath;

    @Before
    public void setUp(){
        basePath = "C:/workspace-example/websocket/target/websocket-1.0-SNAPSHOT/";
        basePath = "C:/workspace-example/danmku-repo/";
        basePath = "F:/workspace/danmuk-repo/download/upload/";
        basePath = "F:/workspace/danmuk-repo/download/upload-20230204/"; // 【突击】来体验港诡实录吧！
        basePath = "F:/workspace/danmuk-repo/download/upload-20230211/"; // 禁书目录与魔法高校Day2~
        basePath = "F:/workspace/danmuk-repo/download/upload-20230212/";
        basePath = "F:/workspace/danmuk-repo/bilibili-danmuk-repo/26545331-20230127-001/";
        basePath = "F:/workspace/danmuk-repo/download/upload-20230213/";
        //basePath = "C:/workspace-example/websocket/target/websocket-1.0-SNAPSHOT/repo/bilibili-danmuk-repo/";
        //basePath = "C:/workspace-example/datas/danmukdemos/";
        //basePath = "C:/workspace-example/datas/danmukdemos/";
        danmukService = new DanmukServiceImpl();
    }

    /**
     * 26122598 杀心成焚酱
     * 26126710 千夏夏灬
     * 21756924 雪绘Yukie
     * 21696950 阿萨Aza
     * 22341433 螃蟹那由
     * 24178057 黑洞
     * 6681391 厄风
     * 920314 邪剑仙
     */

    /**
     * 545068 老实憨厚的笑笑
     * 7734200 哔哩哔哩英雄联盟赛事
     * 6592490 哔哩哔哩UP主服务中心
     *
     * 22384516 呜米
     * 8792912 咩栗
     * 22673512 扇宝
     * 23778409 猫姬琥珀_official
     * 25755118 尾幼mayori
     *
     *
     * 24643640 魔狼咪莉娅
     * 21402309 眞白花音_Official
     * 411318 红晓音Akane
     * 24813281 天音マリー
     * 3822389 有栖Mana_Official
     * 81004 艾尔莎_Channel
     * 21652717 白神遥Haruka
     * 22603245 永雏塔菲
     * 22886883 星瞳_Official
     *
     * 21738461 哔哩哔哩晚会 19:30
     * 23555200 柯洁
     * 23141761 棋手战鹰
     *
     * 21623527 央视频 young在春晚 14:30
     * 22747055 哔哩哔哩拜年纪 19:30
     * 26545331 鲸鱼娘西丝特official 【突击】来体验港诡实录吧！
     */
    @Test
    public void demo1(){
        List<String> roomIds = Arrays.asList(new String[]{

                //"22384516", // 2023的第一场直播就事故了哇！！
                //"8792912", // 呀咩喽
                //"22673512", //
                //"23778409", //
                //"25755118", // 新年第一啵
                //"24643640", //
                //"21402309", // 新年快乐！
                //"411318", // 【环】打过黑剑就下！新年快乐！
                //"24813281", //
                //"3822389", //
                //"81004", // 深夜和动画区的二次元怪叔叔打lol!
                //"21652717", // 天才鹅鸭杀
                "22603245", // 新年好饿

                //"21696950", //
                //"21756924", //
                //"3246070", //

                //"22384516", // 今天可以随便mua主播
                //"8792912", // 马上40岁了
                //"22673512", // 【202扇】扇宝跨年直播
                //"23778409", // 新年吗？？？
                //"25755118", // 健康主包健康还债，物理跨年
                //"24643640", //
                //"21402309", // 歌菜
                //"411318", // 2022年度弹幕颁奖典礼！（本直播间
                //"24813281", //
                //"3822389", //
                //"81004", // 跨年和动画怪叔叔姐姐们玩lol!
                //"21652717", // 和我爱的人一起跨年
                //"22603245", // 新衣跨年派对喵
                //"21738461", // 2022最美的夜 bilibili晚会
                //"23555200", // 八冠王跨年晚会！
                //"23141761", // 1对7指导棋开打！
                //"20230113", // 6592490 哔哩哔哩UP主服务中心 2022百大UP主盛典直播

                //"22384516", // 呜米 2022年印象最深的？
                //"8792912", // 咩栗 眠羊电台
                //"22673512", //
                //"23778409", // 琥珀直播中
                //"25755118", // 健康主播健康还债
                //"24643640", //
                //"21402309", // 回顾1年的白菜
                //"411318", // 今天最后一期！晓闻天下！
                //"24813281", // コラボ配信！
                //"3822389", //
                //"81004", // 一起补完玉子市场吧！
                //"21652717", //
                //"22603245", //
                //"545068","3822389","8792912","21402309","26122598","26126710","7734200-2"
                //"545068","3822389","22384516","8792912","21402309","7734200","22886883","25755118","22673512","23778409","24643640"
                //"7734200-2"
                //"21402309-2", // 百万粉丝感谢回
                //"21402309-3", // 百万粉丝歌回
                //"411318-2", // 卡塔尔世界杯
                //"25755118-2", // 吃饱饱了，小唠一下
                //"24813281", //动画鉴赏+杂谈 唐可可的诱惑
                //"23778409-2", // 歌回
                //"24643640", //MUSE DASH
                //"411318", // 卡塔尔世界杯
                //"21402309", // 二周年纪念3DLive
                //"411318", // 温柔的棉花糖一起过平安夜！
                //"24813281", // 原神
                //"3822389", // 突击歌回
                //"22384516", // 圣诞新呜（小声
                //"8792912", // 咩！栗！哭！栗！
                //"22673512", //
                //"23778409", // 圣诞快乐
                //"25755118", // 和美少龙一起发育小脑
                //"24643640", // ଘ3080联动歌回
                //"21402309", // 圣诞节做料理直播
                //"411318", // 圣诞节
                //"24813281", //
                //"3822389-2", //
                //"81004", // 过圣诞咯！一起来玩惩罚游戏吧！（咬牙）
                //"21652717", //
                //"22603245", //
                //"22384516-20221225-001"
        });
        for (String roomId : roomIds) {
            DanmukUtil.damkuPath = "";
            DanmukUtil.damkuPath += basePath + roomId;
            danmukService.saveDanmuk();
        }
    }

    /**
     *      * 22384516 呜米
     *      * 8792912 咩栗
     *      * 22673512 扇宝
     *      * 23778409 猫姬琥珀_official
     *      * 25755118 尾幼mayori
     *      * 24643640 魔狼咪莉娅
     *      * 21402309 眞白花音_Official
     *      * 411318 红晓音Akane
     *      * 24813281 天音マリー
     *      * 3822389 有栖Mana_Official
     *      * 81004 艾尔莎_Channel
     *      * 21652717 白神遥Haruka
     *      * 22603245 永雏塔菲
     *      * 22886883 星瞳_Official
     *      * 23555200 柯洁
     *      * 23141761 棋手战鹰
     *      * 21696950 阿萨Aza
     *      * 26545331 鲸鱼娘西丝特official 【突击】来体验港诡实录吧！
     */
    @Test
    public void demo3(){
        String roomid = ""; //
        roomid = "22384516"; // 呜米 工作音 勿入 2023-02-14 19:56~22:20
        roomid = "8792912"; // 咩栗 MeUmy双人歌回 2023-02-14 18:53~22:20
        roomid = "22673512"; // 扇宝 说出爱! 扇宝48情人节总选 2023-02-14 19:20~21:36
        roomid = "23778409"; // 猫姬琥珀_official 【bt限】 情人节快乐 2023-02-14 20:59~22:30
        roomid = "25755118"; // 尾幼mayori 浪漫的一天唱点苦情的歌TT 2023-02-14 20:56~23:50
        roomid = "24643640"; // 魔狼咪莉娅 未开播
        roomid = "21402309"; // 眞白花音_Official 【B限】 情人节快乐! 做巧克力 2023-02-14 20:00~00:00
        roomid = "411318"; // 红晓音Akane 没想到吧！！为了晚上不加班我请假！！ 18:00~18:24 /烛光晚班回来玩会hifiRUSH 2023-02-14 20:56~00:00
        roomid = "24813281"; // 天音マリー 未开播
        roomid = "3822389"; // 有栖Mana_Official 情人节 02-14 19:00~20:00
        roomid = "81004"; // 艾尔莎_Channel 情人节但没有情人 2023-02-15 19:57~02:00
        roomid = "21652717"; // 白神遥Haruka 四个美少女分手厨房 2023-02-15 19:48~01:35
        roomid = "22603245"; // 永雏塔菲 情人节一个人情人游戏 2023-02-14 19:44~00:50 / 2023-02-15 02:53~03:53 原神做个日常就跑
        roomid = "22886883"; // 星瞳_Official 【3D】 上了我的车，就是我的人了 2023-02-14 19:50~21:40
        roomid = "23555200"; // 柯洁 未开播
        roomid = "23141761"; // 棋手战鹰 陪大家过节喽~ 2023-02-14 18:56~21:50
        roomid = "21696950"; // 阿萨Aza 情人节哈皮打瓦 2023-02-14 20:00~01:00
        roomid = "26545331"; // 鲸鱼娘西丝特official 【杂谈】 情人节特辑，来听听粉丝们的表白！ 2023-02-14 20:00~23:30
        roomid = "26545331"; // 鲸鱼娘西丝特official 【边聊边玩】 一起在春日里戏水吧！ 2023-02-15 20:00~23:30
        basePath = "F:/workspace/danmuk-repo/download/upload-20230214/";
        basePath += roomid;
        basePath = "F:/workspace/danmuk-repo/download/upload";
        DanmukUtil.damkuPath = "";
        DanmukUtil.damkuPath += basePath;
        danmukService.saveDanmuk();
    }

    @Test
    public void demo2(){
        basePath = "F:/workspace/danmuk-repo/download/upload";
        File file = new File(DanmukUtil.damkuPath);
        System.out.println(file.list().length);
        //danmukService.saveDanmuk();
    }
}
