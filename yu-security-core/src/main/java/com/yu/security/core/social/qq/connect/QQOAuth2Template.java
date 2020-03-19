package com.yu.security.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author yuchl
 * @Date 2020/3/7 0007
 * @Description OAuth2Template
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger log = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        log.info("accessTokenUrl: {};parameters: {}", accessTokenUrl, parameters);
        String result = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        if (StringUtils.contains(result, "\"error\"")){
            throw new AuthenticationServiceException("code to access token error");
        }
        log.info("result: {}", result);
        //access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14。
        String[] params = StringUtils.splitByWholeSeparator(result, "&");
        String accessToken = StringUtils.substringAfter(params[0], "=");
        String refreshToken = StringUtils.substringAfter(params[2], "=");
        Long expiresIn = Long.getLong(StringUtils.substringAfter(params[1], "="));

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
