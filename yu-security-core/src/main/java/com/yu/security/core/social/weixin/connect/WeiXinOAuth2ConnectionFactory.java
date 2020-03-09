package com.yu.security.core.social.weixin.connect;

import com.yu.security.core.social.weixin.api.WeiXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class WeiXinOAuth2ConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param clientId      客户端id
     * @param clientSecret    客户端密码
     * */
    public WeiXinOAuth2ConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new WeiXinOAuth2ServiceProvider(clientId, clientSecret), null);
    }


    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {

        return new OAuth2Connection<WeiXin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeiXinAccessGrant){
            WeiXinAccessGrant weiXinAccessGrant = (WeiXinAccessGrant)accessGrant;
            return weiXinAccessGrant.getOpenId();
        }
        return super.extractProviderUserId(accessGrant);
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinApiAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider(){
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }
}
