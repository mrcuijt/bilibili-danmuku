package org.example.h2;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class CreateH2DataBaseTest {

    private String jdbc;

    private String filePath;

    private String suffix;

    @Before
    public void setUp(){
        filePath = "F:/workspace/danmuk-repo/database/danmuku";

        jdbc = "jdbc:h2:" + filePath;
        jdbc = "jdbc:h2:" + filePath;
        jdbc = "jdbc:h2:tcp://127.0.0.1:3330/" + filePath;

        suffix = ".mv.db";
    }

    @Test
    public void connection(){
        try {
            Class.forName("org.h2.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:h2:C:/workspace/springboot-activity-hxtz-v2.0/db-backup/database/activiti", "sa", "");
            Connection conn = DriverManager.getConnection(jdbc, "sa","");
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void connection2(){
        jdbc = "jdbc:h2:" + filePath;
        connection();
        jdbc = "jdbc:h2:tcp://127.0.0.1:3330/" + filePath;
        connection();
    }



    @Test
    public void delDb(){
        File file = new File(filePath + suffix);
        System.out.println(file.delete());
    }

}
