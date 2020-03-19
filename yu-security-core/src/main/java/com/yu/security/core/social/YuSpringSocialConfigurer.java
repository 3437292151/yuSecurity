package com.yu.security.core.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class YuSpringSocialConfigurer extends SpringSocialConfigurer {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String filterProcessesUrl;

    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public YuSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    protected <T> T postProcess(T object) {
        log.info("YuSpringSocialConfigurer ");
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        if (socialAuthenticationFilterPostProcessor != null){
            socialAuthenticationFilterPostProcessor.processor(filter);
        }
        return (T) filter;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }
}
