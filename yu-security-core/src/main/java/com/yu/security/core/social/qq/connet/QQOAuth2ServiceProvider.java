package com.yu.security.core.social.qq.connet;

import com.yu.security.core.social.qq.api.QQ;
import com.yu.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author yuchl
 * @Date 2020/2/29 0029
 * @Description
 */
public class QQOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String URL_AUTHORIZEURL = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    /**
     * Create a new {@link AbstractOAuth2ServiceProvider}.
     */
    public QQOAuth2ServiceProvider(String appId, String clientSecret) {

        super(new OAuth2Template(appId, clientSecret, URL_AUTHORIZEURL, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
