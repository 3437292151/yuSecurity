package com.yu.security.core.validate;

import com.yu.security.core.properties.SecurityConstants;

/**
 * 验证码类型
 */
public enum ValidateCodeType {

    /**
     * 图片类型
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },

    /**
     * 短信类型
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
