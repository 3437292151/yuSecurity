package com.yu.security.core.properties;

import lombok.Data;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description OAUTH2 客户端配置
 */
@Data
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds = 0;

    private Integer refreshTokenValiditySeconds = 0;

    private String authorizedGrantTypes;

    private String scopes;
}
