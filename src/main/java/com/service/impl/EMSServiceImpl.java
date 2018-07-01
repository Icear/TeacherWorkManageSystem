package com.service.impl;

import com.service.EMSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EMSServiceImpl implements EMSService {
    private static Logger logger = LogManager.getLogger(EMSServiceImpl.class);

    @Override
    public void sendEMS(String phoneNumber, String emsContent) {
        logger.info("send content: " + emsContent + " to" + phoneNumber);
    }
}
