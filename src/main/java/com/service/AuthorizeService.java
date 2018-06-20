package com.service;

import com.entity.TeacherEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * 用于Token管理的Service
 */
public interface AuthorizeService {

    /**
     * 申请Token
     * @param teacher 提出请求的用户身份
     */
    @NotNull String applyToken(@NotNull TeacherEntity teacher);

    /**
     * 根据Token获得用户身份
     * @param token token
     * @return 用户身份，token不存在或失效返回null
     */
    Optional<TeacherEntity> getTokenIdentity(@NotNull String token);

    /**
     * 主动销毁Token
     * @param token token
     */
    void destroyToken(@NotNull String token);
}
