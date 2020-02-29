package com.yu.security.core.validate.image;

import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeException;
import com.yu.security.core.validate.impl.AbstractValidateCodeProcessor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码处理器
 */
@Data
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void removeSessionCode(ServletWebRequest servletWebRequest, ImageCode codeInSession) {
        sessionStrategy.removeAttribute(servletWebRequest, getSessionKey());
    }

    private String getSessionKey() {
        return SESSION_KEY_PREFIX + StringUtils.substringBefore(getClass().getSimpleName(), SecurityConstants.VALIDATE_CODE_PROCESSOR_SUFFIX);
    }

    @Override
    protected ImageCode getSessionCode(ServletWebRequest servletWebRequest) throws ValidateCodeException {
        return (ImageCode) sessionStrategy.getAttribute(servletWebRequest, getSessionKey());
    }

    @Override
    protected void saveCode(ServletWebRequest servletWebRequest, ValidateCode code) {
        ImageCode imageCode = (ImageCode) code;
        sessionStrategy.setAttribute(servletWebRequest, getSessionKey(), imageCode);
    }
}
