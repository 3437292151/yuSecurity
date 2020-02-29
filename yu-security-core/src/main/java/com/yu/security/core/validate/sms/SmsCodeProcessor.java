package com.yu.security.core.validate.sms;

import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeException;
import com.yu.security.core.validate.image.ImageCode;
import com.yu.security.core.validate.impl.AbstractValidateCodeProcessor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Data
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void removeSessionCode(ServletWebRequest servletWebRequest, ValidateCode codeInSession) {
        sessionStrategy.removeAttribute(servletWebRequest, getSessionKey());
    }

    private String getSessionKey() {
        return SESSION_KEY_PREFIX + StringUtils.substringBefore(getClass().getSimpleName(), SecurityConstants.VALIDATE_CODE_PROCESSOR_SUFFIX);
    }

    @Override
    protected ValidateCode getSessionCode(ServletWebRequest servletWebRequest) throws ValidateCodeException {
        return (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, getSessionKey());
    }

    @Override
    protected void saveCode(ServletWebRequest servletWebRequest, ValidateCode code) {
        ValidateCode imageCode = (ValidateCode) code;
        sessionStrategy.setAttribute(servletWebRequest, getSessionKey(), imageCode);
    }
}
