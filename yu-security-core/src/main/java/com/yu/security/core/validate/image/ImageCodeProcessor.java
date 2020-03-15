package com.yu.security.core.validate.image;

import com.yu.security.core.validate.ValidateCodeSender;
import com.yu.security.core.validate.impl.AbstractValidateCodeProcessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码处理器
 */
@Data
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Autowired
    private ValidateCodeSender imageCodeSender;

    @Override
    protected void send(ServletWebRequest servletWebRequest, ImageCode code) {
        imageCodeSender.send(servletWebRequest, code);
    }
}
