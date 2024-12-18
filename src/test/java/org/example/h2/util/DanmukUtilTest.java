package org.example.h2.util;

import org.example.h2.entity.Danmuk;
import org.example.h2.enums.DanmukEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DanmukUtilTest {

    List<File> danmukFiles;

    @Before
    public void setUp() {
        danmukFiles = new ArrayList<>();
        danmukFiles.add(new File("C:/workspace-example/websocket/upload/1583810146232606722.json"));
    }

    @Test
    public void demo1() {
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(danmukFiles);
    }

    @Test
    public void demo2() {
        List<File> danmukFiles = DanmukUtil.getDanmukFiles(null);
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(danmukFiles);
        System.out.println(DanmukUtil.cmd);
    }

    @Test
    public void demo3() {
        System.out.println(DanmukEnum.values().length);
        Arrays.asList(DanmukEnum.values()).stream().map(f -> f.toString()).sorted().forEach(
                f -> {
//                    System.out.println(f + "\t" + DanmukEnum.valueOf(f).getDesc());
                    System.out.println(f + "(\"" + DanmukEnum.valueOf(f).getDesc() + "\"),");
                }
        );
    }

    @Test
    public void demo4() {
        String damkuPath = "D:/tools/upload/upload/";
        //String room = "5279-20240806";
        //File file = new File(damkuPath);
        //List<File> collect = Arrays.asList(file.listFiles()).stream().filter(f -> f.isDirectory()).collect(Collectors.toList());
        //collect.stream().forEach(f -> demo5(f));
        //collect.stream().forEach(f -> System.out.println(f.getName()));
        String[] datas = {
//                "11631685-20241211",
//                "24052921-20241211",
//                "26919",
//                "26919-20241202",
//                "26919-20241203",
//                "26919-20241204",
//                "26919-20241205",
//                "26919-20241206",
//                "26919-20241212"

//                "6431492"
//                "24160384-20240805", // !!!
//                "24160384-20240807", // !!!
//                "5279-20240807", // !!!
//                "1774974750-20241024",
//                "1921505348-20241024",
//                "21414905-20241025",
//                "21987615-20240815",
//                "21987615-20241024",
//                "22719662-20240805",
//                "22719662-20240806",
//                "22719662-20240807",
//                "24160384-20240806",
//                "24323935-20240805",
//                "7734200-20241029",
//                "7401280-20241216"
                "21414905"
//                "24780517",
//                "24780517-20241028",
//                "26796998-20240805",
//                "26919",
//                "26919-20240806",
//                "26919-20240807",
//                "26919-20241028",
//                "26919-202410282",
//                "30627986",
//                "30627986-20241028",
//                "31691919",
//                "31691919-20241028",
//                "37034-20240806",
//                "37034-20240807",
//                "4350043-20241025",
//                "5134127-20241028",
//                "5151048-20241025",
//                "5279-20240806",
//                "5279-20241025",
//                "7401280-20240812",
//                "7401280-20241028",
//                "7401280-202410282",
//                "7734200",
//                "7734200-20241028",
//                "81004-20241025",
        };
        List<String> paths = new ArrayList<>();
        //Arrays.asList(datas).stream().forEach(f -> demo5(new File(damkuPath, f)));
        Arrays.asList(datas).stream().forEach(f -> {
            File file = new File(damkuPath, f);
            File[] files = file.listFiles();
            Arrays.stream(files).map(f1 -> f1.getAbsolutePath()).forEach(paths::add);
        });
        try (
                FileWriter fileWriter = new FileWriter(new File(damkuPath, "jsons.txt"));
                BufferedWriter bw = new BufferedWriter(fileWriter)) {
            paths.stream().forEach(f -> {
                try {
                    bw.write(f);
                    bw.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void demo5(File file) {
        System.out.println(file.getAbsoluteFile());
        List<File> danmukFiles = DanmukUtil.getDanmukFiles(file.getAbsolutePath());
        //List<Danmuk> danmuk = DanmukUtil.getDanmuk(danmukFiles);
        for (File danmukFile : danmukFiles) {
            List<Danmuk> danmuk = DanmukUtil.getDanmuk(Arrays.asList(danmukFile));
            List<Danmuk> collect = danmuk.stream().filter(f -> f.getDanmukType().equals(
                    DanmukEnum.ACTIVITY_BANNER_CHANGE
                            .toString())).collect(Collectors.toList());
            if (collect.size() > 0) {
                Danmuk danmuk1 = collect.get(0);
                System.out.println(danmuk1.getFilePath());
                System.out.println(danmuk1.getDanmukMsg());
//                Danmuk danmuk1 = collect.stream().filter(f -> f.getDanmukMsg().contains("10243150")).findFirst().orElse(null);
//                if (danmuk1!= null){
//                    System.out.println(danmuk1.getDanmukMsg());
//                }
//                collect.stream().forEach(f -> System.out.println(f.getDanmukMsg()));
                System.exit(0);
                break;
            }
        }
    }

    public void demo6() {
        String file = "D:/tools/upload/upload/jsons.txt";
        try (FileReader fr = new FileReader(new File(file));
             BufferedReader br = new BufferedReader(fr)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                demo7(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void demo7(String file) {
        System.out.println(file);
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(Arrays.asList(new File(file)));
        List<Danmuk> collect = danmuk.stream().filter(f -> f.getDanmukType().equals(
                DanmukEnum.ACTIVITY_BANNER_CHANGE
                        .toString())).collect(Collectors.toList());
        if (collect.size() > 0) {
            Danmuk danmuk1 = collect.get(0);
            System.out.println(danmuk1.getFilePath());
            System.out.println(danmuk1.getDanmukMsg());
//                Danmuk danmuk1 = collect.stream().filter(f -> f.getDanmukMsg().contains("10243150")).findFirst().orElse(null);
//                if (danmuk1!= null){
//                    System.out.println(danmuk1.getDanmukMsg());
//                }
//                collect.stream().forEach(f -> System.out.println(f.getDanmukMsg()));
            System.exit(0);
            //break;
        }
    }

    Map<String, Boolean> acct = new LinkedHashMap<>();

    @Test
    public void demo8() {
        acct.put("DANMU_MSG", false);
        String file = "D:/tools/upload/upload/jsons.txt";
        try (FileReader fr = new FileReader(new File(file));
             BufferedReader br = new BufferedReader(fr)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                demo9(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void demo9(String file) {
        List<Danmuk> danmuk = DanmukUtil.getDanmuk(Arrays.asList(new File(file)));
        if (acct.values().stream().filter(f -> f.booleanValue() == false).count() == 0) {
            System.exit(0);
        }
        acct.entrySet().stream().filter(f -> f.getValue().booleanValue() == false)
                .collect(Collectors.toList()).stream().forEach(type -> {
            List<Danmuk> collect = danmuk.stream().filter(f -> f.getDanmukMsg().contains(String.format("\"%s\"", type.getKey())))
                    .collect(Collectors.toList());
            if (collect.size() > 0) {
                Danmuk danmuk1 = collect.get(0);
                System.out.println(danmuk1.getFilePath() + " " + type.getKey());
                System.out.println(danmuk1.getDanmukMsg());
//                acct.put(type.getKey(), Boolean.TRUE);
            }
        });
    }

    public void printA() {
        acct.entrySet().stream().forEach(f -> {
            System.out.println(f);
        });
    }
}
