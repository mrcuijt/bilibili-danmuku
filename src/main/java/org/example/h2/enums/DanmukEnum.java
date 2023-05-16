package org.example.h2.enums;

import lombok.Getter;

/**
 * 作者：忘月沁 https://www.bilibili.com/read/cv13055247/ 出处：bilibili
 */
@Getter
public enum DanmukEnum {

    ROOM_CHANGE("房间信息"),
    ROOM_SKIN_MSG("房间皮肤信息"),
    ROOM_BLOCK_MSG("房间块消息"),

    NOTICE_MSG("直播间广播"),
    STOP_LIVE_ROOM_LIST("停止的直播间信息"),

    LIVE("直播开始"),
    PREPARING("主播准备中"),
    ROOM_REAL_TIME_MESSAGE_UPDATE("主播粉丝信息更新"),
    HOT_RANK_CHANGED("主播实时活动排名"),

    WELCOME("欢迎加入房间"),
    WELCOME_GUARD("欢迎房管加入直播间"),
    INTERACT_WORD("进入直播间"),
    ENTRY_EFFECT("进入特效"),

    DANMU_MSG("弹幕内容"),
    SEND_GIFT("赠送礼物"),
    COMBO_SEND("赠送礼物"),
    GUARD_BUY("购买舰长"),
    USER_TOAST_MSG("续费舰长"),
    SUPER_CHAT_MESSAGE("超级留言"),
    SUPER_CHAT_MESSAGE_JPN("超级留言-JP"),
    SUPER_CHAT_MESSAGE_DELETE("超级留言移除"),
    WIDGET_BANNER("小部件横幅"),
    PK_BATTLE_ENTRANCE("PK战斗入口"),
    GUARD_HONOR_THOUSAND("守卫荣誉千舰"),
    POPULARITY_RED_POCKET_NEW("人气红包"),
    POPULARITY_RED_POCKET_START("人气红包开始"),
    POPULARITY_RED_POCKET_WINNER_LIST("人气红包中奖名单"),
    DANMU_AGGREGATION("弹幕聚合"),
    COMMON_NOTICE_DANMAKU("公共弹幕通知"),

    VOICE_JOIN_ROOM_COUNT_INFO("连麦信息数"),
    VOICE_JOIN_LIST("连麦信息列表"),
    VOICE_JOIN_STATUS("连麦状态"),
    VOICE_JOIN_SWITCH("连麦切换"),
    VIDEO_CONNECTION_MSG("视频连线消息"),
    VIDEO_CONNECTION_JOIN_END("视频连线结束"),

    HOT_ROOM_NOTIFY("热门直播间通知"),
    HOT_RANK_SETTLEMENT("热榜"),
    HOT_RANK_SETTLEMENT_V2("热榜"),
    HOT_RANK_CHANGED_V2("热榜更新"),
    ONLINE_RANK_V2("高能榜数据"),
    ONLINE_RANK_TOP3("用户进高能榜前三"),
    ONLINE_RANK_COUNT("高能榜数量更新"),
    PLAY_TAG("标签"),
    TRADING_SCORE(""),
    WATCHED_CHANGE("观看人数更新"),
    LIKE_INFO_V3_UPDATE("点赞数更新"),
    // （天选时刻）
    LIVE_INTERACTIVE_GAME("互动游戏"),

    GOTO_BUY_FLOW("去购买"),
    RING_STATUS_CHANGE("振铃状态更改"),
    RING_STATUS_CHANGE_V2("振铃状态更改"),
    ANCHOR_LOT_CHECKSTATUS("天选时刻检查状态"),
    ANCHOR_LOT_START("天选时刻开始"),
    ANCHOR_LOT_END("天选时刻结束"),
    ANCHOR_LOT_AWARD("天选时刻中奖"),
    FULL_SCREEN_SPECIAL_EFFECT("全屏特效"),
    SHOPPING_EXPLAIN_CARD(""),
    SHOPPING_CART_SHOW(""),
    HOT_BUY_NUM(""),

    RANK_REM(""),
    FUNCTION_CARD("互动卡片"),
    DANMU_TAG_CHANGE(""),
    LIVE_PANEL_CHANGE_CONTENT(""),
    LIKE_SO_HOT(""),
    LIKE_INFO_V3_CLICK(""),
    SPECIAL_GIFT(""),
    GUARD_ACHIEVEMENT_ROOM(""),
    LIVE_OPEN_PLATFORM_GAME(""),
    GIFT_PANEL_PLAN(""),
    GIFT_STAR_PROCESS(""),
    RECOMMEND_CARD(""),
    WARNING(""),
    POPULAR_RANK_CHANGED(""),
    SUPER_CHAT_ENTRANCE(""),
    SHOPPING_BUBBLES_STYLE(""),
    WIN_ACTIVITY(""),
    ACTIVITY_BANNER_CHANGE(""),
    ACTIVITY_BANNER_CHANGE_V2(""),
    REENTER_LIVE_ROOM(""),
    BOX_ACTIVITY_START(""),
    AREA_RANK_CHANGED(""),
    ANCHOR_NORMAL_NOTIFY(""),
    SYS_MSG(""),
    room_admin_entrance(""),
    ROOM_ADMINS(""),
    WIDGET_GIFT_STAR_PROCESS(""),
    ROOM_SILENT_OFF(""),
    ;

    String desc;

    DanmukEnum(String desc) {
        this.desc = desc;
    }
}
