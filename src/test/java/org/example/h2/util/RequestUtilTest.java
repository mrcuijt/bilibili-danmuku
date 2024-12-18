package org.example.h2.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.util.Base64;

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

    @Test
    public void demo() throws Exception {
        String base64 = "CiI2ZGRjMzI5YmZkNDY5ZjAzNWZmYThkNTVmODY0NzU4ZjkxEAEYGSD///8HKgg5MDc2NmU3ZDIh6KaB55S15YiA5Lmf5pqX5aSc5pS25Ymy5Ye65p2l5oqKOJ/T4tmGMUihlII+YgCKAQCaARAKCEVCRDFDMzI2EIef1qMGogGYAQj56g0SDOiOieS4neaPkOS6miJKaHR0cHM6Ly9pMS5oZHNsYi5jb20vYmZzL2ZhY2UvMzBmZGMzNmNhYTAxZmY3NzM3ODRkNjU1ODk2ODA4YmM0ZjQ2MmI4My5qcGc4kE5AAVoeCBgSBEPphbEgy6hpMP/RnwM4y6hpQJK7ygJIA1ABYg8IHxDx0YEFGgY+NTAwMDBqAHIAqgEVCMWMBBIKQ+mFseOBp+OBmRjIwK4K";
        byte[] decode = Base64.getDecoder().decode(base64);
        String str = new String(decode, "UTF-8");
        System.out.println(str);
    }

    @Test
    public void serids(){
        String basePath = "F:/workspace/danmuk-repo/download/upload";
        String charset = "UTF-8";
        StringBuffer strb = new StringBuffer();
        int i = 0;
        while (i < 1000){
            strb.append(IdWorker.getIdStr());
            strb.append("\r\n");
            i++;
        }
        try(FileOutputStream fos = new FileOutputStream(new File(basePath, "sers.txt"))) {
            fos.write(strb.toString().getBytes(charset));
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
