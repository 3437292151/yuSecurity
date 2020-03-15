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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

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

    @Autowired
    private SpringSocialConfigurer yuSpringSocialConfigurer;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

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
            .apply(yuSpringSocialConfigurer)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository ())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .and()
            .sessionManagement()//session管理
                //.invalidSessionUrl("/session/invalid")//无效session跳转url
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())//session最大数量
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())//最大session数之后是否阻止登录
                .expiredSessionStrategy(sessionInformationExpiredStrategy)//session过期后策略
                .and()
                .and()
            .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler(logoutSuccessHandler)

                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                       securityProperties.getBrowser().getLoginPage(),
                       securityProperties.getBrowser().getSignupUrl(),
                       securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                       securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                       securityProperties.getBrowser().getSignOutUrl(),
                       SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        "/user/regist")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();

    }


}
