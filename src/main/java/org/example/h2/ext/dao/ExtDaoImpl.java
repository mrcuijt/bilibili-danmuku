package org.example.h2.ext.dao;

import org.example.h2.entity.Danmuk;
import org.example.h2.ext.entity.DanmukExart;
import org.example.h2.ext.entity.DanmukOrigin;
import org.example.h2.util.DateUtil;
import org.example.h2.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ExtDaoImpl implements ExtDao {

    public static final String URL = "jdbc:h2:tcp://127.0.0.1:3330/D:/tools/mybatis-generate-tools-exart/db/danmuku-exart";

    @Override
    public int batchInsertDanmukOrigin(List<DanmukOrigin> danmukOrigins) {
        final String sql = "INSERT INTO DANMUK_ORIGIN (ID,BATCH_NO,ROOM_ID,ORIGIN_FILE,ORIGIN_SER,COMMAND,ORIGIN_CONTENT,CREATE_TIME) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = JdbcUtil.getConn(URL);) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (DanmukOrigin danmukOrigin : danmukOrigins) {
                ps.setString(1, danmukOrigin.getId());
                ps.setString(2, danmukOrigin.getBatchNo());
                ps.setString(3, danmukOrigin.getRoomId());
                ps.setString(4, danmukOrigin.getOriginFile());
                ps.setInt(5, danmukOrigin.getOriginSer().intValue());
                ps.setString(6, danmukOrigin.getCommand());
                ps.setClob(7, JdbcUtil.getClob(danmukOrigin.getOriginContent()));
                ps.setTimestamp(8, (Objects.isNull(danmukOrigin.getCreateTime()) ? null : Timestamp.valueOf(DateUtil.toLocalDateTime(danmukOrigin.getCreateTime().getTime(), 0))));
                ps.addBatch();
            }
            try {
                int[] ints = ps.executeBatch();
                conn.commit();
                return ints.length;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int batchInsertDanmukExart(List<DanmukExart> danmukExarts) {
        final String sql = "INSERT INTO DANMUK_EXART (ID,REF_ID,SPEC_FILED,SPEC_VAL,SPEC_VAL_TYPE,SPEC_VAL_SER,SPEC_LEVEL,SPEC_LEVEL_NAME,SPEC_OUTER_CONTAINER_TYPE,CREATE_TIME) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = JdbcUtil.getConn(URL);) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (DanmukExart danmukExart : danmukExarts) {
                ps.setString(1, danmukExart.getId());
                ps.setString(2, danmukExart.getRefId());
                ps.setString(3, danmukExart.getSpecFiled());
                ps.setClob(4, JdbcUtil.getClob(danmukExart.getSpecVal()));
                ps.setString(5, danmukExart.getSpecValType());
                ps.setInt(6, danmukExart.getSpecValSer().intValue());
                ps.setInt(7, danmukExart.getSpecLevel().intValue());
                ps.setString(8, danmukExart.getSpecLevelName());
                ps.setString(9, danmukExart.getSpecOuterContainerType());
                ps.setTimestamp(10, (Objects.isNull(danmukExart.getCreateTime()) ? null : Timestamp.valueOf(DateUtil.toLocalDateTime(danmukExart.getCreateTime().getTime(), 0))));
                ps.addBatch();
            }
            try {
                int[] ints = ps.executeBatch();
                conn.commit();
                return ints.length;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
