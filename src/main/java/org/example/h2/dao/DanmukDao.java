package org.example.h2.dao;

import org.example.h2.entity.Danmuk;

import java.util.List;

public interface DanmukDao {

    /**
     * 批量添加
     *
     * @param danmukList
     * @return
     */
    int addBatch(List<Danmuk> danmukList);

}
