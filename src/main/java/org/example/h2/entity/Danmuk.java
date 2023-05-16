package org.example.h2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Danmuk {

    /**
     * 主键
     */
    private String id;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 弹幕数据
     */
    private String danmuk;

    /**
     * 弹幕消息
     */
    private String danmukMsg;

    /**
     * 弹幕类型
     */
    private String danmukType;

    /**
     * 弹幕类型
     */
    private String danmukUserId;

    /**
     * 弹幕类型
     */
    private String danmukUserName;

    /**
     * 弹幕类型
     */
    private LocalDateTime danmukTime;

}
