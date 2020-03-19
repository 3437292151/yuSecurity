package com.yu.security.app;

/**
 * app认证异常
 */
public class AppSecurityException extends RuntimeException {
    public AppSecurityException(String message) {
        super(message);
    }
}
