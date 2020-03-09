package com.yu.security.core.social.weixin.connect;

import com.yu.security.core.social.weixin.api.WeiXin;
import com.yu.security.core.social.weixin.api.impl.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author yuchl
 * @Date 2020/2/29 0029
 * @Description
 */
public class WeiXinOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    private static final String URL_AUTHORIZEURL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private String appId;

    /**
     * Create a new {@link AbstractOAuth2ServiceProvider}.
     */
    public WeiXinOAuth2ServiceProvider(String appId, String clientSecret) {

        super(new OAuth2Template(appId, clientSecret, URL_AUTHORIZEURL, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken, appId);
    }
}
