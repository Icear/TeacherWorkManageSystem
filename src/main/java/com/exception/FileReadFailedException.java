package com.exception;

public class FileReadFailedException extends RuntimeException {
    public FileReadFailedException() {
    }

    public FileReadFailedException(String message) {
        super(message);
    }

    public FileReadFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadFailedException(Throwable cause) {
        super(cause);
    }

    public FileReadFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
