package com.yu.security.core.validate.sms;

import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeSender;
import com.yu.security.core.validate.impl.AbstractValidateCodeProcessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

@Data
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private ValidateCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest servletWebRequest, ValidateCode code) {
        smsCodeSender.send(servletWebRequest, code);
    }
}
