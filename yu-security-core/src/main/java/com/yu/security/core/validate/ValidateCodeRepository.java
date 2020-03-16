package com.yu.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeRepository {

    /**
     * 保存
     * @param request
     * @param validateCode
     * @param validateCodeType
     */
    void saveCode(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return ValidateCode
     */
    ValidateCode getValidate(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 删除验证码
     * @param request
     * @param validateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);

}
