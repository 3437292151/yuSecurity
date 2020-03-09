package com.yu.security.core.social.weixin.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Author yuchl
 * @Date 2020/3/7 0007
 * @Description OAuth2Template
 */
public class WeiXinOAuth2Template extends OAuth2Template {

    private final String clientId;

    private final String clientSecret;

    private final String accessTokenUrl;

    private Logger log = LoggerFactory.getLogger(getClass());

    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.set("openid", clientId);
        params.set("secret", clientSecret);

        params.set("code", authorizationCode);
        params.set("redirect_uri", redirectUri);
        params.set("grant_type", "authorization_code");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.set("appid", clientId);
        params.set("secret", clientSecret);
        params.set("refresh_token", refreshToken);
        params.set("grant_type", "refresh_token");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    public AccessGrant authenticateClient(String scope) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.set("appid", clientId);
        params.set("secret", clientSecret);

        params.set("grant_type", "client_credentials");
        if (scope != null) {
            params.set("scope", scope);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    @Override
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response)  {

        return new WeiXinAccessGrant(accessToken, null, refreshToken, expiresIn, (String)response.get("openid"));
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().remove(0);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter (Charset.forName("UTF-8")));
        return restTemplate;
    }
}
