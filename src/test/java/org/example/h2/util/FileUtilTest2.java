package org.example.h2.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilTest2 {

    String roomid;

    String basePath;

    String target;

    String prefix = "part";

    @Before
    public void setUp() {
        basePath = "C:/workspace-example/websocket/target/websocket-1.0-SNAPSHOT/22603245";
        target = "C:/workspace-example/websocket/target/websocket-1.0-SNAPSHOT/22603245/temp";
        basePath = "C:/workspace-example/danmku-repo/22603245";
        target = "C:/workspace-example/danmku-repo/22603245/temp";
        target = "F:/workspace/danmuk-repo/download/upload";
        basePath = "F:/workspace/danmuk-repo/bilibili-danmuk-repo/26545331-20230127-001";
        target = "F:/workspace/danmuk-repo/bilibili-danmuk-repo";
        basePath = "F:/workspace/danmuk-repo/bilibili-danmuk-repo/26545331-20230211-001";
        basePath = "F:/workspace/danmuk-repo/bilibili-danmuk-repo/26545331-20230212-001";
        String bath = "22384516-20230214-001";
        bath = "8792912-20230214-001";
        bath = "22673512--20230214-001";
        bath = "23778409-20230214-001";
        bath = "21402309-20230214-001";
        bath = "411318-20230214-001";
        bath = "411318-20230214-002";
        bath = "81004-20230214-001";
        bath = "21652717-20230214-001";
        bath = "22603245-20230214-001";
        bath = "22886883-20230214-001";
        bath = "23141761-20230214-001";
        bath = "21696950-20230214-001";
        bath = "26545331-20230214-001";
        basePath = "F:/workspace/danmuk-repo/bilibili-danmuk-repo/";
        basePath += bath;
//        target = basePath + "/temp";

        File file = new File(target);
        if (!file.exists()) {
            file.mkdirs();
        }
        //basePath = "C:/workspace-example/datas/danmukdemos/";
        //roomid = "26122598";
        //basePath += roomid;
    }

    @Test
    public void createTime() {
        long ending = 1676393459000L;
        File file = new File(basePath);
        Stream.of(file.listFiles()).forEach(f -> {
            try {
                FileTime fileTime = Files.readAttributes(f.toPath(), BasicFileAttributes.class).lastModifiedTime();
                if (DateUtil.toLocalDateTime(fileTime.toMillis(), 0).compareTo(DateUtil.toLocalDateTime(ending, 0)) < 0) {
                    Files.move(f.toPath(), new File(target, f.getName()).toPath(), StandardCopyOption.ATOMIC_MOVE);
                    System.out.println(DateUtil.format(DateUtil.toLocalDateTime(fileTime.toMillis(), 0)));
                }
                //Files.move(f.toPath(), new File(basePath, roomid + "-" + f.getName()).toPath(), StandardCopyOption.ATOMIC_MOVE);
                //System.out.println(DateUtil.format(DateUtil.toLocalDateTime(fileTime.toMillis(), 0)));
                //System.out.println(f.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void size() {
        File file = new File(target);
        System.out.println(file.listFiles().length);


        long ending1 = 1670504760000L;
        long ending2 = 1670507205487L;
        long ending3 = 1670512699639L;
        System.out.println(ending2 > ending1);

        System.out.println(DateUtil.format(DateUtil.toLocalDateTime(ending3, 0)));
    }

    @Test
    public void demo() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_1);
        Date parse = sdf.parse("2023-02-15 00:50:59");
        System.out.println(parse.getTime());
        // 2023-02-15 00:50:59 1676393459000
        // 2023-02-14 18:24:59 1676370299000
        // 2023-01-02 02:30:59 1672597859000
    }

    @Test
    public void splitSize() throws IOException {
        File file = new File(basePath);
        List<File> files = Arrays.asList(file.listFiles());
        int total = files.size();
        int max = 10000;
        int split = (total % max > 0) ? total / max + 1 : total / max;
        StringBuffer strb = new StringBuffer();
        /*
        // 性能非常慢
        for (int i = 1; i <= split; i++) {
            final int cur = i;
            List<File> collect = files.stream().filter(f -> {
                int index = files.indexOf(f);
                return index > max * (cur - 1) && index < max * cur;
            }).collect(Collectors.toList());
            collect.stream().forEach(o -> strb.append(o + "\r\n"));
        }
        */
        // 快速
        for (int i = 1; i <= split; i++) {
            final int cur = i;
            List<File> collect = new ArrayList<>();
            for(int j = max * (cur - 1); (j < max * cur && j < files.size()); j++){
                collect.add(files.get(j));
            }
            String movePath = partSize(prefix, i);
            moveFiles(movePath, collect);
            //collect.stream().forEach(o -> strb.append(o + "\r\n"));
        }
//        FileOutputStream fos = new FileOutputStream(new File(target, "list-2.txt"));
//        fos.write(strb.toString().getBytes("UTF-8"));
//        fos.flush();
//        fos.close();
        /*
        StringBuffer strb = new StringBuffer();
        files.stream().forEach(o -> strb.append(o + "\r\n"));
        FileOutputStream fos = new FileOutputStream(new File(target, "list.txt"));
        fos.write(strb.toString().getBytes("UTF-8"));
        fos.flush();
        fos.close();
        */
    }

    public void moveFiles(String movePath, List<File> files) throws IOException {
        File moveDir = new File(basePath, movePath);
        if(!moveDir.exists())
            moveDir.mkdirs();

        for (File file : files) {
            File fileTarget = new File(moveDir.getAbsolutePath(), file.getName());
            Files.move(file.toPath(), fileTarget.toPath(), StandardCopyOption.ATOMIC_MOVE);
        }
    }

    @Test
    public void testSplit() {
        for (int i = 1; i <= 10; i++) {
            split(i);
        }
    }

    public void split(int cur) {
        System.out.println(partSize(prefix, cur));
    }

    public String partSize(String prefix, int cur) {
        int max = 1000;
        return String.format("%s-%s", prefix, String.valueOf(1000 + cur).substring(1));
    }

    @Test
    public void delFolderTest(){
        basePath = "xxxxx";
    }
}
