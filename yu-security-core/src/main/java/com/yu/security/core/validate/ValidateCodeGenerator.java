package com.yu.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码生成器
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest servletWebRequest);
}
