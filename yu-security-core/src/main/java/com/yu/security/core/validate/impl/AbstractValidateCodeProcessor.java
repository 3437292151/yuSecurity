package com.yu.security.core.validate.impl;

import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.validate.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public abstract  class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private Map<String, ValidateCodeSender> validateCodeSenderMap;

    Logger log = LoggerFactory.getLogger(getClass());



    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void createCode(ServletWebRequest servletWebRequest) {
        C code = generate(servletWebRequest);

        saveCode(servletWebRequest, code);

        send(servletWebRequest, code);
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

        ValidateCodeType processorType = getValidateCodeType();
        //获取前段码
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        //获取后端码
        C codeInSession = getSessionCode(servletWebRequest);
        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()){
            removeSessionCode(servletWebRequest, codeInSession);
            throw new ValidateCodeException("验证码已过期");
        }

        //前后端码对比
        if(!StringUtils.equals(codeInRequest, codeInSession.getCode())){
            removeSessionCode(servletWebRequest, codeInSession);
            throw new ValidateCodeException("验证码不匹配");
        }
        removeSessionCode(servletWebRequest, codeInSession);

    }

    private ValidateCodeType getValidateCodeType() {
        String codeProcessor = StringUtils.substringBefore(getClass().getSimpleName(), SecurityConstants.VALIDATE_CODE_PROCESSOR_SUFFIX).toUpperCase();
        log.info("codeProcessor: {}" + codeProcessor);
        return ValidateCodeType.valueOf(codeProcessor);
    }

    protected void removeSessionCode(ServletWebRequest servletWebRequest, C codeInSession){
        validateCodeRepository.remove(servletWebRequest, getValidateCodeType());
    }

    protected C getSessionCode(ServletWebRequest servletWebRequest) throws ValidateCodeException{
        return (C) validateCodeRepository.getValidate(servletWebRequest, getValidateCodeType());
    }

    protected abstract void send(ServletWebRequest servletWebRequest, C code);

    protected void saveCode(ServletWebRequest servletWebRequest, ValidateCode code){
        validateCodeRepository.saveCode(servletWebRequest, code, getValidateCodeType());
    }

    private C generate(ServletWebRequest request){
        String validateType = getValidateCodeType().getParamNameOnValidate();
        String generatorName = validateType + "Generator";
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
        if (validateCodeGenerator == null){
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

}
