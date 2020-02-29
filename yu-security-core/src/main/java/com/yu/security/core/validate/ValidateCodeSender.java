package com.yu.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeSender {

    void send(ServletWebRequest servletWebRequest, ValidateCode code) throws ValidateCodeException;
}
