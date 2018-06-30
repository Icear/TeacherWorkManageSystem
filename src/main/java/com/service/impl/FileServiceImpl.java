package com.service.impl;

import com.service.FileService;
import com.util.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LogManager.getLogger(FileServiceImpl.class);
    private FileUtils fileUtils;

    @Autowired
    public FileServiceImpl(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    @Override
    public String saveFile(byte[] file, String fileName) {

        //生成文件名,附加UUID避免冲突
        fileName = UUID.randomUUID().toString() + "-" + fileName;

        logger.debug(fileName);

        Path trueFileName = Paths.get(fileName);
        //保存数据
        fileUtils.save(file, trueFileName);

        return null;
    }

    @Override
    public byte[] readFile(String fileName) {
        return fileUtils.getFile(Paths.get(fileName));
    }
}
