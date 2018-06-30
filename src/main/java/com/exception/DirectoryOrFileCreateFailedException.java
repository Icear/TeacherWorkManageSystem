package com.exception;

/**
 * 文件或目录创建失败异常
 */
public class DirectoryOrFileCreateFailedException extends RuntimeException {
    public DirectoryOrFileCreateFailedException() {
    }

    public DirectoryOrFileCreateFailedException(String message) {
        super(message);
    }

    public DirectoryOrFileCreateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectoryOrFileCreateFailedException(Throwable cause) {
        super(cause);
    }

    public DirectoryOrFileCreateFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
