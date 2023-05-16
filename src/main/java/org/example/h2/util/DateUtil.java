package org.example.h2.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    public static final String GMT_PLUS_8 = "GMT+8";

    public static LocalDateTime toLocalDateTime(Long timestamp19) {
        return toLocalDateTime(String.valueOf(timestamp19));
    }

    public static LocalDateTime toLocalDateTime(String timestamp19) {
        Long timeZone13 = Long.valueOf(String.valueOf(timestamp19).substring(0, 13));
        Long nanoTime = Long.valueOf(String.valueOf(timestamp19).substring(13));
        return toLocalDateTime(timeZone13, nanoTime);
    }

    public static LocalDateTime toLocalDateTime(long millis, long nanos) {
        LocalDateTime ldt = Instant.ofEpochMilli(millis).atZone(ZoneId.of(GMT_PLUS_8)).toLocalDateTime();
        LocalDateTime ldt2 = ldt.plusNanos(nanos);
        return ldt2;
    }

    public static String format(LocalDateTime ldt) {
        return format(ldt, DATE_FORMAT_1);
    }

    public static String format(LocalDateTime ldt, String format) {
        return DateTimeFormatter.ofPattern(format).format(ldt);
    }

    private static final String CLOCK_1 = "%d秒";
    private static final String CLOCK_2 = "%d分";
    private static final String CLOCK_3 = "%d分%d秒";
    private static final String CLOCK_4 = "%d时%d分%d秒";

    public static String getSuperChatMessageTime(Integer second) {
        if (second < 60) {
            return String.format(CLOCK_1, second);
        } else if (second == 60) {
            return String.format(CLOCK_2, 1);
        } else if (second > 60 && second < 3600) {
            int m = second / 60;
            int s = second % 60;
            return String.format(CLOCK_3, m, s);
        } else {
            int h = second / 3600;
            int m = (second % 3600) / 60;
            int s = (second % 3600) % 60;
            return String.format(CLOCK_4, h, m, s);
        }
    }

}
