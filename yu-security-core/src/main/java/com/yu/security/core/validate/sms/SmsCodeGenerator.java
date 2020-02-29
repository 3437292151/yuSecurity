package com.yu.security.core.validate.sms;

import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeGenerator;
import com.yu.security.core.validate.image.ImageCode;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 短信验证码生成器
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    Logger log = LoggerFactory.getLogger(getClass());

    private SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
