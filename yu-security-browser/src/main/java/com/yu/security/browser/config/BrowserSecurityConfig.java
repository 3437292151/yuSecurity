package com.yu.security.browser.config;

import com.yu.security.browser.anthentication.YuAuthenticationFailureHandler;
import com.yu.security.browser.anthentication.YuAuthenticationSuccessHandler;
import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-17
 * @Description:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private Filter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {

       http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
               .formLogin()
        //http.httpBasic()
               .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
               .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .and()
                .authorizeRequests()
               .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                       securityProperties.getBrowser().getLoginPage(),
                       "/code/image")
               .permitAll()
                .anyRequest()
                .authenticated()
               .and().csrf().disable();

    }


}
