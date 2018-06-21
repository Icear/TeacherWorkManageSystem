package com.service.impl;

import com.entity.TeacherEntity;
import com.entity.TokenInfo;
import com.exception.IdentityNotFoundException;
import com.provider.TokenInfoProvider;
import com.service.AuthorizeService;
import com.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    private static final Logger logger = LogManager.getLogger(AuthorizeServiceImpl.class);

    private final TeacherService teacherService;

    private final TokenInfoProvider tokenInfoProvider;

    /**
     * 过期时间，单位：毫秒
     */
    @Value("${Token.expireTime}")
    private long expireTime;


    @Autowired
    public AuthorizeServiceImpl(TeacherService teacherService, TokenInfoProvider tokenInfoProvider) {
        this.teacherService = teacherService;
        this.tokenInfoProvider = tokenInfoProvider;
    }

    @Override
    public @NotNull String applyToken(@NotNull TeacherEntity teacher) {

        Map<String, TokenInfo> tokenMap = tokenInfoProvider.getTokenMap();

        logger.info("receive token apply from ", teacher);

        //检查teacher有效性，如果有效生成token，如果无效

        TokenInfo tokenInfo = new TokenInfo();

        Optional<TeacherEntity> teacherEntityOptional = teacherService.findTeacher(teacher.getId());
        tokenInfo.setIdentity(teacherEntityOptional.orElseThrow(IdentityNotFoundException::new));
        tokenInfo.setLastActiveTime(Calendar.getInstance());

        logger.debug("Issued token for: ", tokenInfo);

        String token;
        do {
            //出现重复Token则重新生成
            token = generateToken();
        } while (!tokenMap.containsKey(token));

        tokenMap.put(token, tokenInfo);//保存Token数据，开始生效
        return token;
    }

    @Override
    public Optional<TeacherEntity> getTokenIdentity(@NotNull String token) {
        Map<String, TokenInfo> tokenMap = tokenInfoProvider.getTokenMap();

        /*
         * 获得token，并检查是否过期，过期则删除Token并返回空
         */
        Optional<TokenInfo> tokenInfo = Optional.ofNullable(tokenMap.get(token));
        TeacherEntity identity = null;
        if (tokenInfo.isPresent()) {
            if (checkTokenEffect(tokenInfo.get().getLastActiveTime())) {
                identity = tokenInfo.get().getIdentity();
            } else {
                tokenMap.remove(token);
            }
        }
        return Optional.ofNullable(identity);
    }

    @Override
    public void destroyToken(@NotNull String token) {
        Map<String, TokenInfo> tokenMap = tokenInfoProvider.getTokenMap();
        Optional.ofNullable(tokenMap.get(token)).ifPresent(v -> tokenMap.remove(token));
    }

    /**
     * 生成Token
     *
     * @return token
     */
    private @NotNull String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 检查Token是否有效
     *
     * @param lastActiveTime 上一次活跃时间
     * @return 是否有效
     */
    private boolean checkTokenEffect(@NotNull Calendar lastActiveTime) {
        Calendar currentTime = Calendar.getInstance();
        return currentTime.getTimeInMillis() - lastActiveTime.getTimeInMillis() < expireTime;
    }
}
