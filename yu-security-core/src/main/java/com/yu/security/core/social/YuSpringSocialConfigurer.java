package com.yu.security.core.social;

import com.yu.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

@Component
public class YuSpringSocialConfigurer extends SpringSocialConfigurer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    protected <T> T postProcess(T object) {
        log.info("YuSpringSocialConfigurer");
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(securityProperties.getSocial().getFilterProcessesUrl());
        filter.setSignupUrl(securityProperties.getSocial().getQq().getProviderId());

        return object;
    }


}
