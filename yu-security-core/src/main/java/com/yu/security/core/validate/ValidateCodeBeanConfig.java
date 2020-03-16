package com.yu.security.core.validate;

import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.image.ImageCodeGenerator;
import com.yu.security.core.validate.image.ImageCodeProcessor;
import com.yu.security.core.validate.image.ImageCodeSender;
import com.yu.security.core.validate.impl.SessionValidateCodeRepository;
import com.yu.security.core.validate.sms.SmsCodeGenerator;
import com.yu.security.core.validate.sms.SmsCodeProcessor;
import com.yu.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-24
 * @Description: 验证码Bean配置
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public ValidateCodeSender smsCodeSender(){
        SmsCodeSender smsCodeSender = new SmsCodeSender();
        smsCodeSender.setSecurityProperties(securityProperties);
        return smsCodeSender;
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeSender")
    public ValidateCodeSender imageCodeSender(){
        ImageCodeSender imageCodeSender = new ImageCodeSender();
        imageCodeSender.setSecurityProperties(securityProperties);
        return imageCodeSender;
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeProcessor")
    public ValidateCodeProcessor imageCodeProcessor(){
        ImageCodeProcessor imageCodeProcessor = new ImageCodeProcessor();
        return imageCodeProcessor;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeProcessor")
    public ValidateCodeProcessor smsCodeProcessor(){
        SmsCodeProcessor smsCodeProcessor = new SmsCodeProcessor();
        return smsCodeProcessor;
    }

    @Bean
    @ConditionalOnMissingBean(name = "validateCodeRepository")
    public ValidateCodeRepository validateCodeRepository(){
        return new SessionValidateCodeRepository();
    }

}
