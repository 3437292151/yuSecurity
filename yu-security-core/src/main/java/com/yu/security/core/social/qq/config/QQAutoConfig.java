package com.yu.security.core.social.qq.config;

import com.yu.security.core.properties.QQProperties;
import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.social.YuConnectView;
import com.yu.security.core.social.qq.connect.QQOAuth2ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

@Configuration
@ConditionalOnProperty(prefix = "yu.security.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qq = securityProperties.getSocial().getQq();
        log.info("ProviderId:{}, AppId:{} ,AppSecret: {}", qq.getProviderId(), qq.getAppId(), qq.getAppSecret() );
        return new QQOAuth2ConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

    @Bean({"connect/qqConnected", "connect/qqConnect"})
    @ConditionalOnMissingBean(name = "qqConnectView")
    public View qqConnectView(){
        return new YuConnectView();
    }
}
