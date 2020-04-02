package com.yu.security.core.properties;

import lombok.Data;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description OAuth2 参数配置
 */
@Data
public class OAuth2Properties {

    private OAuth2ClientProperties client[] = {};

    private String jwtSigningKey;
}
