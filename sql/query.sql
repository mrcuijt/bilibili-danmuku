
-- ID, ROOM_ID, DANMUK, DANMUK_TYPE, DANMUK_USER_ID, DANMUK_USER_NAME, DANMUK_TIME
-- ID, ROOM_ID, DANMUK, DANMUK_MSG, DANMUK_TYPE, DANMUK_USER_ID, DANMUK_USER_NAME, DANMUK_TIME
-- T2.*
-- T2.ID, T2.ROOM_ID, T2.DANMUK, T2.DANMUK_TYPE, T2.DANMUK_USER_ID, T2.DANMUK_USER_NAME, T2.DANMUK_TIME
-- T2.ID, T2.ROOM_ID, T2.DANMUK, T2.DANMUK_MSG, T2.DANMUK_TYPE, T2.DANMUK_USER_ID, T2.DANMUK_USER_NAME, T2.DANMUK_TIME

/**
  ===================================================================================
  数据查询: 弹幕消息 / 查询条件: 弹幕类型、时间、分页
  ===================================================================================
 */
SET @LIMIT_BEGIN 0;
SET @LIMIT_END 1000;
SET @DANMUK_TYPE = 'DANMU_MSG';
SET @BEGIN_TIME = '2023-12-31 20:00:00';
SET @END_TIME = '2023-12-31 22:59:59';
--SET @END_TIME = '2023-12-31 23:59:59';
SELECT
    T2.ID, T2.ROOM_ID, T2.DANMUK, T2.DANMUK_TYPE, T2.DANMUK_USER_ID, T2.DANMUK_USER_NAME, T2.DANMUK_TIME
FROM (
     SELECT MAX(ID) AS ID, DANMUK
     FROM LOAN_SYS_DANMUK
     WHERE DANMUK_TYPE = @DANMUK_TYPE
       AND DANMUK_TIME BETWEEN FORMATDATETIME(@BEGIN_TIME, 'yyyy-MM-dd HH:mm:ss') AND FORMATDATETIME(@END_TIME, 'yyyy-MM-dd HH:mm:ss')
     GROUP BY DANMUK
         LIMIT @LIMIT_BEGIN, @LIMIT_END
 ) T1 LEFT JOIN LOAN_SYS_DANMUK T2 ON T1.ID = T2.ID;


/**
  ===================================================================================
  数据查询: 弹幕类型 / 分组查询: 弹幕类型
  ===================================================================================
 */
SELECT DANMUK_TYPE, COUNT(1)
FROM LOAN_SYS_DANMUK
GROUP BY DANMUK_TYPE
ORDER BY COUNT(1) DESC;


/**
  ===================================================================================
  数据查询: 弹幕消息 / 查询条件: 弹幕类型、时间、分页
  ===================================================================================
 */
SET @LIMIT_BEGIN 0;
SET @LIMIT_END 1000;
SET @DANMUK_TYPE = 'DANMU_MSG';
SET @BEGIN_TIME = '2023-12-31 20:00:00';
SET @END_TIME = '2023-12-31 22:59:59';
--SET @END_TIME = '2023-12-31 23:59:59';
SELECT
    ID, ROOM_ID, DANMUK, DANMUK_TYPE, DANMUK_USER_ID, DANMUK_USER_NAME, DANMUK_TIME
FROM LOAN_SYS_DANMUK
WHERE DANMUK_TYPE = @DANMUK_TYPE
  AND DANMUK_TIME BETWEEN FORMATDATETIME(@BEGIN_TIME, 'yyyy-MM-dd HH:mm:ss') AND FORMATDATETIME(@END_TIME, 'yyyy-MM-dd HH:mm:ss')
    LIMIT @LIMIT_BEGIN, @LIMIT_END;



