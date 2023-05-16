package org.example.h2.parser;

import org.example.h2.enums.DanmukEnum;

public class DanmukHolder {

    public static DanmukParser getDanmukParser(DanmukEnum danmukEnum) {
        switch (danmukEnum) {
            case DANMU_MSG:
                return (DanmuMsgParser) () -> danmukEnum;
            case DANMU_AGGREGATION:
                return (DanmuAggregationParser) () -> danmukEnum;
            case NOTICE_MSG:
                return (NoticeMsgParser) () -> danmukEnum;
            case PLAY_TAG:
                return (PlayTagParser) () -> danmukEnum;
            case SEND_GIFT:
                return (SendGiftParser) () -> danmukEnum;
            case WATCHED_CHANGE:
                return (WatchedChangeParser) () -> danmukEnum;
            case ROOM_REAL_TIME_MESSAGE_UPDATE:
                return (RroomRealTimeMessageUpdateParser) () -> danmukEnum;
            case LIKE_INFO_V3_UPDATE:
                return (LikeInfoV3UpdateParser) () -> danmukEnum;
            case STOP_LIVE_ROOM_LIST:
                return (StopLiveRoomListParser) () -> danmukEnum;
            case INTERACT_WORD:
                return (InteractWordParser) () -> danmukEnum;
            case ONLINE_RANK_COUNT:
                return (OnlineRankCountParser) () -> danmukEnum;
            case ONLINE_RANK_V2:
                return (OnlineRankV2Parser) () -> danmukEnum;
            case ENTRY_EFFECT:
                return (EntryEffectParser) () -> danmukEnum;
            case COMBO_SEND:
                return (ComboSendParser) () -> danmukEnum;
            case ONLINE_RANK_TOP3:
                return (OnlineRankTop3Parser) () -> danmukEnum;
            case SUPER_CHAT_MESSAGE:
                return (SuperChatMessageParser) () -> danmukEnum;
            case SUPER_CHAT_MESSAGE_JPN:
                return (SuperChatMessageJpnParser) () -> danmukEnum;
            case SUPER_CHAT_MESSAGE_DELETE:
                return (SuperChatMessageDeleteParser) () -> danmukEnum;
            case GUARD_BUY:
                return (GuardBuyParser) () -> danmukEnum;
            case USER_TOAST_MSG:
                return (UserToastMsgParser) () -> danmukEnum;
            case HOT_ROOM_NOTIFY:
                return (HotRoomNotifyParser) () -> danmukEnum;
            case HOT_RANK_CHANGED:
                return (HotRankChangedParser) () -> danmukEnum;
            case HOT_RANK_CHANGED_V2:
                return (HotRankChangedV2Parser) () -> danmukEnum;
            case HOT_RANK_SETTLEMENT:
                return (HotRankSettlementParser) () -> danmukEnum;
            case HOT_RANK_SETTLEMENT_V2:
                return (HotRankSettlementV2Parser) () -> danmukEnum;
            case COMMON_NOTICE_DANMAKU:
                return (CommonNoticeDanmakuParser) () -> danmukEnum;
            case GUARD_HONOR_THOUSAND:
                return (GuardHonorThousandParser) () -> danmukEnum;
            case PREPARING:
                return (PreparingParser) () -> danmukEnum;
            case ROOM_CHANGE:
                return (RoomChangeParser) () -> danmukEnum;
            case LIVE:
                return (LiveParser) () -> danmukEnum;
            case LIVE_INTERACTIVE_GAME:
                return (LiveInteractiveGameParser) () -> danmukEnum;
            case WIDGET_BANNER:
                return (WidgetBannerParser) () -> danmukEnum;
            case ROOM_BLOCK_MSG:
                return (RoomBlockMsgParser) () -> danmukEnum;
            case PK_BATTLE_ENTRANCE:
                return (PkBattleEntranceParser) () -> danmukEnum;
            case POPULARITY_RED_POCKET_NEW:
                return (PopularityRedPocketNewParser) () -> danmukEnum;
            case POPULARITY_RED_POCKET_START:
                return (PopularityRedPocketStartParser) () -> danmukEnum;
            case POPULARITY_RED_POCKET_WINNER_LIST:
                return (PopularityRedPocketWinnerListParser) () -> danmukEnum;
            case VOICE_JOIN_ROOM_COUNT_INFO:
                return (VoiceJoinRoomCountInfoParser) () -> danmukEnum;
            case VOICE_JOIN_LIST:
                return (VoiceJoinListParser) () -> danmukEnum;
            case VOICE_JOIN_STATUS:
                return (VoiceJoinStatusParser) () -> danmukEnum;
            case VOICE_JOIN_SWITCH:
                return (VoiceJoinSwitchParser) () -> danmukEnum;
            case GOTO_BUY_FLOW:
                return (GotoBuyFlowParser) () -> danmukEnum;
            case RING_STATUS_CHANGE:
                return (RingStatusChangeParser) () -> danmukEnum;
            case RING_STATUS_CHANGE_V2:
                return (RingStatusChangeV2Parser) () -> danmukEnum;
            case ANCHOR_LOT_CHECKSTATUS:
                return (AnchorLotCheckStatusParser) () -> danmukEnum;
            case ANCHOR_LOT_START:
                return (AnchorLotStartParser) () -> danmukEnum;
            case ANCHOR_LOT_END:
                return (AnchorLotEndParser) () -> danmukEnum;
            case ANCHOR_LOT_AWARD:
                return (AnchorLotAwardParser) () -> danmukEnum;
            case FULL_SCREEN_SPECIAL_EFFECT:
                return (FullScreenSpecialEffectParser) () -> danmukEnum;
            default:
                return (DataParser) () -> danmukEnum;
        }
    }
}
