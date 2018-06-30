package com.exception;

/**
 * 实体更新失败异常
 */
public class EntityUpdateFailedException extends RuntimeException {
    public EntityUpdateFailedException() {
    }

    public EntityUpdateFailedException(String message) {
        super(message);
    }

    public EntityUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public EntityUpdateFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
