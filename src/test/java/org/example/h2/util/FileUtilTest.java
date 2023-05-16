package org.example.h2.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileUtilTest {

    String encoding = "UTF-8";

    String ext = ".html";

    String basePath;

    String targetPath;

    @Before
    public void setUp() {

        basePath = "E:/BaiduNetdiskDownload/docs-24_r02/docs";

        targetPath = "E:/BaiduNetdiskDownload/docs-24_r02/target-docs";
        
        basePath = "D:/tools/android/Documents";
        targetPath = "D:/tools/android/TargetDocuments";

        initTargetPath();
    }


    @Test
    public void modifyRef() {
        File source = new File(basePath);
        copyFile(source);
    }

    public void copyFile(File file) {
        if (file.isDirectory()) {
            File targetDir = new File(getTargetPath(file));
            if (!targetDir.exists()) targetDir.mkdirs();
            for (File item : Arrays.asList(file.listFiles())) {
                copyFile(item);
            }
            return;
        }

        if (file.getName().endsWith(ext)) {
            String content = FileUtil.getContent(file.getAbsolutePath(), encoding);
//            content = content.replaceAll("href=\"http", "href=\"./http")
//                    .replaceAll("href=\"/", "href=\"./")
//                    .replaceAll("src=\"http", "src=\"./http")
//                    .replaceAll("src=\"/", "src=\"./")
//                    .replaceAll("srcset", "asrcset")
//                    .replaceAll("url\\(/", "url(" + relPrefix(sssize(file)));
            content = content.replaceAll("<section", "<section active ");
            FileUtil.writeConfig(new File(getTargetPath(file)), content);
            return;
        }

        File target = new File(getTargetPath(file));
        //if(target.exists()) target.delete();
        if (target.exists()) return;
        try {
            Files.copy(file.toPath(), target.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("error with " + target.getAbsolutePath());
        }
    }

    public String getTargetPath(File file) {
        return targetPath + file.getAbsolutePath()
                .replace("\\", "/")
                .replace(basePath, "");
    }

    public int sssize(File file) {
        return file.getAbsolutePath()
                .replace("\\", "/")
                .replace(basePath, "").split("/").length;
    }

    public String relPrefix(int size) {
        size -= 2;
        if (size == 1) {
            return "./";
        }
        String prefix = "";
        for (int i = 0; i < size; i++) {
            prefix += "../";
        }
        return prefix;
    }

    public void initTargetPath() {
        File file = new File(targetPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
