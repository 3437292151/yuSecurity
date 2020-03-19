package com.yu.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Author yuchl
 * @Date 2020/3/18 0018
 * @Description 社交后置处理器
 */
public interface SocialAuthenticationFilterPostProcessor {

    void processor(SocialAuthenticationFilter filter);
}
