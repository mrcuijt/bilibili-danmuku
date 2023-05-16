package org.example.h2.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtilTest {

    List<Date> dates = new ArrayList<>();

    List<JSONObject> jsons = new ArrayList<>();

    @Before
    public void setUp(){
        ZoneId zoneId = ZoneId.of(DateUtil.GMT_PLUS_8);
        LocalDateTime ldt = LocalDateTime.now();
        dates.add(new Date(ldt.atZone(zoneId).toInstant().toEpochMilli()));
        for(int i = 0; i < 10; i++){
            ldt = ldt.plus(1, ChronoUnit.MINUTES);
            dates.add(new Date(ldt.atZone(zoneId).toInstant().toEpochMilli()));

            JSONObject json = new JSONObject();
            json.put("date", new Date(ldt.atZone(zoneId).toInstant().toEpochMilli()));
            json.put("group", i % 2);
            jsons.add(json);
        }
    }

    @Test
    public void demo(){
        dates.stream().forEach(f -> {
            System.out.println(DateUtil.format(DateUtil.toLocalDateTime(f.getTime(), 0)));
        });

        System.out.println("Sorted");
        List<Date> desc = dates.stream().sorted(Comparator.comparing(Date::getTime).reversed()).collect(Collectors.toList());
        desc.stream().forEach(f -> {
            System.out.println(DateUtil.format(DateUtil.toLocalDateTime(f.getTime(), 0)));
        });
    }

    @Test
    public void demo2(){
        jsons.stream().forEach(System.out::println);
        System.out.println("Sorted");
        jsons = jsons.stream()
                .sorted(
                        ((Comparator<JSONObject>) (o1, o2) -> {
                            return o1.getInteger("group").compareTo(o2.getInteger("group"));
                        }).thenComparing(f -> f.getDate("date"))
                ).collect(Collectors.toList());
        jsons.stream().forEach(System.out::println);
    }

    @Test
    public void demo3(){
        LocalDateTime ldt  = LocalDateTime.now();
        System.out.println(ldt);
    }

}
