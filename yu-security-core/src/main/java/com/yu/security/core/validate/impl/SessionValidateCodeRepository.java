package com.yu.security.core.validate.impl;

import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeRepository;
import com.yu.security.core.validate.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author yuchl
 * @Date 2020/3/16 0016
 * @Description session验证码持久化
 */
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void saveCode(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, validateCodeType.getParamNameOnValidate(), code);
    }

    @Override
    public ValidateCode getValidate(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, validateCodeType.getParamNameOnValidate());
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request, validateCodeType.getParamNameOnValidate());
    }
}
