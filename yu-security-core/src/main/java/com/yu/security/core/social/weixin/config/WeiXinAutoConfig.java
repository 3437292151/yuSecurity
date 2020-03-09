package com.yu.security.core.social.weixin.config;

import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.properties.WeiXinProperties;
import com.yu.security.core.social.qq.connect.QQOAuth2ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "yu.security.social.weixin", name = "appId")
public class WeiXinAutoConfig extends SocialAutoConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        WeiXinProperties weixin = securityProperties.getSocial().getWeixin();
        log.info("ProviderId:{}, AppId:{} ,AppSecret: {}", weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret() );
        return new QQOAuth2ConnectionFactory(weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret());
    }
}
