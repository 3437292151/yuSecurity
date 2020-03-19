package com.yu.security.core.properties;

import lombok.Data;
import org.apache.commons.lang.Validate;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-18
 * @Description: 认证参数
 */
@ConfigurationProperties(prefix = "yu.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();

}
