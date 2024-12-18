package org.example.h2.service;

import org.example.h2.dao.DanmukDao;
import org.example.h2.dao.DanmukDaoImpl;
import org.example.h2.util.DanmukUtil;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class DanmukServiceImpl implements DanmukService {

    private DanmukDao danmukDao = new DanmukDaoImpl();

    @Override
    public void saveDanmuk() {
        int batch = 100;
        int current = 0;
        List<File> danmukFiles = DanmukUtil.getDanmukFiles(null);
        do {
            List<File> fileList = Collections.emptyList();
            if(danmukFiles.size() > current && danmukFiles.size() > current + batch){
                fileList = danmukFiles.subList(current, current + batch);
            } else {
                fileList = danmukFiles.subList(current, danmukFiles.size() - 1);
            }
            current += batch;
            DanmukUtil.getDanmuk(fileList);
            //danmukDao.addBatch(DanmukUtil.getDanmuk(fileList));
        } while (danmukFiles.size() > current);
    }

}
