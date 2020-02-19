package com.yu.security.core.validate.service.impl;

import com.yu.security.core.validate.service.ValidateCodeService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 图片验证码 服务
 */
@Service
public class ValidateImageCodeServiceImpl implements ValidateCodeService {


    @Override
    public void createCode(ServletWebRequest servletWebRequest) {

    }
}
