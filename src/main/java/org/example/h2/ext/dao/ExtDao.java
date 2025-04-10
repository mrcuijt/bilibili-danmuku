package org.example.h2.ext.dao;

import org.example.h2.ext.entity.DanmukExart;
import org.example.h2.ext.entity.DanmukOrigin;

import java.util.List;

public interface ExtDao {

    /**
     * 批量插入 原始数据行
     *
     * @param danmukOrigins
     */
    int batchInsertDanmukOrigin(List<DanmukOrigin> danmukOrigins);

    /**
     * 批量插入 原始数据行特征值
     *
     * @param danmukExarts
     */
    int batchInsertDanmukExart(List<DanmukExart> danmukExarts);

}
