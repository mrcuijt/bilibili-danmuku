package org.example.h2.ext.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DanmukOrigin {
    /**
     * 标识 ID
     */
    private String id;

    /**
     * 批次号 BATCH_NO
     */
    private String batchNo;

    /**
     * 直播房间号 ROOM_ID
     */
    private String roomId;

    /**
     * 数据来源 ORIGIN_FILE
     */
    private String originFile;

    /**
     * 数据来源序号 ORIGIN_SER
     */
    private BigDecimal originSer;

    /**
     * 指令 COMMAND
     */
    private String command;

    /**
     * 创建时间 CREATE_TIME
     */
    private Date createTime;

    /**
     * 数据内容 ORIGIN_CONTENT
     */
    private String originContent;
}