package com.service;

public interface EMSService {
    /**
     * 发送短信，即输出内容
     *
     * @param phoneNumber 发送目标号码
     * @param emsContent 短信内容
     */
    void sendEMS(String phoneNumber, String emsContent);
}
