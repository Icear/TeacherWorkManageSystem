package com.service;

public interface EMSService {
    /**
     * 发送短信，即输出内容
     *
     * @param emsContent 短信内容
     */
    void sendEMS(String emsContent);
}
