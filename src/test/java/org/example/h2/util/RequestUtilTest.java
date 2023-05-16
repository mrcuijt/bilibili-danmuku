package org.example.h2.util;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

public class RequestUtilTest {

    String url;

    SimpleURLConnection util;

    @Before
    public void setUp(){

        util = new SimpleURLConnection();

        url = "https://github.com/mrcuijt/bilibili-danmuk-repo";
    }

    @Test
    public void connect() {
        try {
            HttpURLConnection connection = util.getConnection(url);
            connection.connect();
            URLConnectionUtil.readHeader(connection.getHeaderFields());
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while( (len = is.read(buffer, 0, buffer.length)) != -1 ){
                baos.write(buffer, 0, len);
            }
            System.out.println(new String(baos.toByteArray(), "UTF-8"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void retry(){
        while (true){
            connect();
        }
    }
}
