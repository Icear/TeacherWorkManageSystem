package com.task;

import com.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Token过期检查任务
 */
@Component
public class TokenCheckTask {

    private final AuthorizeService authorizeService;


    @Autowired
    public TokenCheckTask(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }


}
