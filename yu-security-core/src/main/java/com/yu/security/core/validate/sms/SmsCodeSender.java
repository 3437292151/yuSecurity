package com.yu.security.core.validate.sms;

import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeSender;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码 发送器
 */
@Data
public class SmsCodeSender implements ValidateCodeSender {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void send(ServletWebRequest servletWebRequest, ValidateCode code) {
        log.info("短信验证码：{}",code.getCode());
    }
}
