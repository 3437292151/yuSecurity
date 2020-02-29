package com.yu.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     * @param servletWebRequest
     */
    void createCode(ServletWebRequest servletWebRequest);

    /**
     * 校验 验证码
     * @param servletWebRequest
     */
    void validate(ServletWebRequest servletWebRequest);
}
