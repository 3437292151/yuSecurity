package com.yu.security.core.social.qq.connect;

import com.yu.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQOAuth2ConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param clientId      客户端id
     * @param clientSecret    客户端密码
     * */
    public QQOAuth2ConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new QQOAuth2ServiceProvider(clientId, clientSecret), new QQApiAdapter());
    }
}
