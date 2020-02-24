package com.yu.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author yuchl
 * @Date 2020/2/23 0023
 * @Description
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
