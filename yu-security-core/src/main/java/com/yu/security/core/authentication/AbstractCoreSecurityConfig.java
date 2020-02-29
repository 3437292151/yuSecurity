package com.yu.security.core.authentication;

import com.yu.security.core.properties.SecurityConstants;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security认证配置
 */
public abstract class AbstractCoreSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM);
    }
}
