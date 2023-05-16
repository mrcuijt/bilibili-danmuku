package org.example.h2.dao;

import org.example.h2.entity.Danmuk;
import org.example.h2.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class DanmukDaoImpl implements DanmukDao {

    /**
     * 批量添加
     *
     * @param danmukList
     * @return
     */
    @Override
    public int addBatch(List<Danmuk> danmukList){
        String sql = "INSERT INTO LOAN_SYS_DANMUK VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = JdbcUtil.getConn();){
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Danmuk danmuk : danmukList) {
                ps.setString(1, danmuk.getId());
                ps.setString(2, danmuk.getRoomId());
                ps.setClob(3, JdbcUtil.getClob(danmuk.getDanmuk()));
                ps.setClob(4, JdbcUtil.getClob(danmuk.getDanmukMsg()));
                ps.setString(5, danmuk.getDanmukType());
                ps.setString(6, danmuk.getDanmukUserId());
                ps.setString(7, danmuk.getDanmukUserName());
                ps.setTimestamp(8, (Objects.isNull(danmuk.getDanmukTime()) ? null : Timestamp.valueOf(danmuk.getDanmukTime())));
                ps.addBatch();
            }
            try {
                int[] ints = ps.executeBatch();
                conn.commit();
                return ints.length;
            } catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

}
