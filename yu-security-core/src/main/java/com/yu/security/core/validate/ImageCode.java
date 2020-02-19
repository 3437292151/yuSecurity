package com.yu.security.core.validate;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 图片验证码对象
 */
@Data
public class ImageCode {

    //验证码
    private String code;

    //到期时间
    private LocalDateTime expireTime;

    private BufferedImage image;

    public ImageCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(String code, LocalDateTime expireTime, BufferedImage image) {
        this.code = code;
        this.expireTime = expireTime;
        this.image = image;
    }

    public ImageCode(String code, Integer expireIn, BufferedImage image) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        this.image = image;
    }

    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
