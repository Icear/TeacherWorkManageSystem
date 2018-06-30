package com.util;

import com.exception.FileReadFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传工具
 */
@Component
public class FileUtils {
    private static String truePath; //储存上传目录
    private Logger logger = LogManager.getLogger(FileUtils.class);
    @Value("${UPLOAD_DIRECTORY}")
    private String uploadDirectory;

    /**
     * 保存数据至文件
     *
     * @param bytes 数据
     * @param path  文件名
     */
    public void save(byte[] bytes, Path path) {
        //相对路径转绝对路径
        String truePath = getUploadPath();

        path = Paths.get(truePath + path);
        logger.debug(path);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new DirectoryIteratorException(e);
        }
    }

    /**
     * 读取文件
     *
     * @param path 文件名
     * @return 数据
     */
    public byte[] getFile(Path path) {
        String truePath = getUploadPath();

        path = Paths.get(truePath + path);
        logger.debug(path);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            //文件读取失败
            throw new FileReadFailedException(e);
        }
    }


    @NotNull
    private String getUploadPath() {
        if (truePath != null) {
            //如果已经生存过上传目录，则直接返回
            return truePath;
        }

        //生成目录并创建
        truePath = System.getProperty("TeacherWorkManageSystem.webapp") + uploadDirectory;
        logger.debug(truePath);
        Path path1 = Paths.get(uploadDirectory);
        if (!Files.isDirectory(path1)) {
            try {
                Files.createDirectories(path1);
            } catch (IOException e) {
                throw new DirectoryIteratorException(e);
            }
        }
        return truePath;
    }

}
