package com.yu.security.core.validate.image;

import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.image.ImageCode;
import com.yu.security.core.validate.ValidateCode;
import com.yu.security.core.validate.ValidateCodeException;
import com.yu.security.core.validate.ValidateCodeSender;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图片验证码 发送器
 */
@Data
public class ImageCodeSender implements ValidateCodeSender {

    private SecurityProperties securityProperties;

    @Override
    public void send(ServletWebRequest servletWebRequest, ValidateCode code) {
        ImageCode imageCode = (ImageCode) code;
        try {
            ImageIO.write(imageCode.getImage(), securityProperties.getCode().getImage().getFormatName(), servletWebRequest.getResponse().getOutputStream());
        } catch (IOException e) {
            throw new ValidateCodeException("图片验证码发送失败");
        }
    }
}
