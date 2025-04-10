package org.example.h2.ext.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DanmukExart {
    /**
     * 标识 ID
     */
    private String id;

    /**
     * 引用标识 REF_ID
     */
    private String refId;

    /**
     * 特征标签 SPEC_FILED
     */
    private String specFiled;

    /**
     * 特征值类型 SPEC_VAL_TYPE
     */
    private String specValType;

    /**
     * 特征值序号 SPEC_VAL_SER
     */
    private BigDecimal specValSer;

    /**
     * 特征层级 SPEC_LEVEL
     */
    private BigDecimal specLevel;

    /**
     * 特征层级名 SPEC_LEVEL_NAME
     */
    private String specLevelName;

    /**
     * 特征所在容器类型 SPEC_OUTER_CONTAINER_TYPE
     */
    private String specOuterContainerType;

    /**
     * 创建时间 CREATE_TIME
     */
    private Date createTime;

    /**
     * 特征值 SPEC_VAL
     */
    private String specVal;
}