package com.yu.security.core.validate.image;

import com.yu.security.core.validate.ValidateCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 图片验证码对象
 */
@Getter
@Setter
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(String code, LocalDateTime expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(String code, Integer expireIn, BufferedImage image) {
        super(code, expireIn);
        this.image = image;
    }

}
