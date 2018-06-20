package com.exception;

/**
 * 身份未找到-异常
 */
public class IdentityNotFoundException extends RuntimeException {
    public IdentityNotFoundException() {
    }

    public IdentityNotFoundException(String message) {
        super(message);
    }

    public IdentityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityNotFoundException(Throwable cause) {
        super(cause);
    }

    public IdentityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
