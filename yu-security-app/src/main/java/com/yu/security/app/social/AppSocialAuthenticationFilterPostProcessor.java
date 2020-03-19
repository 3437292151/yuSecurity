package com.yu.security.app.social;

import com.yu.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author yuchl
 * @Date 2020/3/18 0018
 * @Description app社交后置处理器
 */
@Component("socialAuthenticationFilterPostProcessor")
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler yuAuthenticationSuccessHandler;

    @Override
    public void processor(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(yuAuthenticationSuccessHandler);
    }
}
