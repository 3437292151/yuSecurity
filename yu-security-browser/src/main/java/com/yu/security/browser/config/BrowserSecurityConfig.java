package com.yu.security.browser.config;


import com.yu.security.core.authentication.AbstractCoreSecurityConfig;
import com.yu.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-17
 * @Description:
 */
@Configuration
public class BrowserSecurityConfig extends AbstractCoreSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository (){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http
            .apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository ())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                       securityProperties.getBrowser().getLoginPage(),
                       SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();

    }


}
