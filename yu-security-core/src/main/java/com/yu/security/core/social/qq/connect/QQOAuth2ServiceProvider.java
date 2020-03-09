package com.yu.security.core.social.qq.connect;

import com.yu.security.core.social.qq.api.QQ;
import com.yu.security.core.social.qq.api.impl.QQImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author yuchl
 * @Date 2020/2/29 0029
 * @Description
 */
public class QQOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String URL_AUTHORIZEURL = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    /**
     * Create a new {@link AbstractOAuth2ServiceProvider}.
     */
    public QQOAuth2ServiceProvider(String appId, String clientSecret) {
        super(new QQOAuth2Template(appId, clientSecret, URL_AUTHORIZEURL, URL_ACCESS_TOKEN));
        log.info("appId:{} , clientSecret: {}", appId, clientSecret);
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
