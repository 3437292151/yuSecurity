package com.yu.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidateCodeHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessor getValidateCodeProcessor(String type) {
        String name = type + "CodeProcessor";
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(name);
        if(validateCodeProcessor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return validateCodeProcessor;
    }

    public ValidateCodeProcessor getValidateCodeProcessor(ValidateCodeType validateCodeType) {
        String name = validateCodeType.name().toLowerCase() + "CodeProcessor";
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(name);
        if(validateCodeProcessor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return validateCodeProcessor;
    }
}
