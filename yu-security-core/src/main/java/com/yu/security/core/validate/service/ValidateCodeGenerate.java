package com.yu.security.core.validate.service;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码生成器
 */
public interface ValidateCodeGenerate {

    void createCode(ServletWebRequest servletWebRequest) throws IOException;
}
