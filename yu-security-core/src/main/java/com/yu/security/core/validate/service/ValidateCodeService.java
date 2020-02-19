package com.yu.security.core.validate.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码 服务层
 */
public interface ValidateCodeService {

    void createCode(ServletWebRequest servletWebRequest);
}
