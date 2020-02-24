package com.yu.security.core.properties;

import lombok.Data;

/**
 * @Author yuchl
 * @Date 2020/2/23 0023
 * @Description 验证码参数
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

}
