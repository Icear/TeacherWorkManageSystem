package com.provider;

import com.entity.TokenInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * TokenInfo的提供者
 */
public interface TokenInfoProvider {
    @NotNull Map<String, TokenInfo> getTokenMap();
}
