package com.provider.impl;

import com.entity.TokenInfo;
import com.provider.TokenInfoProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存实现的TokenInfo储存机制，后期使用KV缓存后再实现另外一个版本
 */
@Repository
public class MemoryTokenInfoProvider implements TokenInfoProvider {
    private Map<String, TokenInfo> tokenMap;

    public MemoryTokenInfoProvider() {
        tokenMap = new HashMap<>();
    }

    @Override
    public @NotNull Map<String, TokenInfo> getTokenMap() {
        return tokenMap;
    }
}
