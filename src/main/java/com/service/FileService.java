package com.service;

/**
 * 文件服务
 */
public interface FileService {

    /**
     * 保存文件
     *
     * @param file     文件数据
     * @param fileName 文件原始名
     * @return 实际文件名
     */
    String saveFile(byte[] file, String fileName);

    /**
     * 读取文件
     *
     * @param fileName 文件名
     * @return 文件数据
     */
    byte[] readFile(String fileName);

}
