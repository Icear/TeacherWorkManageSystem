package com.task;

import com.entity.TokenInfo;
import com.provider.TokenInfoProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

/**
 * Token过期检查任务
 */
@Component
public class TokenCheckTask {

    private final TokenInfoProvider tokenInfoProvider;
    /**
     * 过期时间，单位：毫秒
     */
    @Value("${TOKEN_EXPIRE_TIME}")
    private long expireTime;

    @Autowired
    public TokenCheckTask(TokenInfoProvider tokenInfoProvider) {
        this.tokenInfoProvider = tokenInfoProvider;
    }


    @Scheduled(cron = "* */20 * * * *")
    public void checkTokens() {
        Map<String, TokenInfo> tokenMap = tokenInfoProvider.getTokenMap();
        tokenMap.forEach((token, tokenInfo) -> {
            if (!checkTokenEffect(tokenInfo.getLastActiveTime())) {
                tokenMap.remove(token);
            }
        });
    }

    /**
     * 检查Token是否有效
     *
     * @param lastActiveTime 上一次活跃时间
     * @return 是否已有效
     */
    private boolean checkTokenEffect(@NotNull Calendar lastActiveTime) {
        Calendar currentTime = Calendar.getInstance();
        return currentTime.getTimeInMillis() - lastActiveTime.getTimeInMillis() < expireTime;
    }
}
