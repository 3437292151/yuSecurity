package com.yu.security.core.validate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码对象
 */
@Data
public class ValidateCode implements Serializable {

    //验证码
    private String code;

    //到期时间
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
