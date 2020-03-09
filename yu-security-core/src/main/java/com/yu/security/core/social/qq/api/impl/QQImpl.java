package com.yu.security.core.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.security.core.social.qq.api.QQ;
import com.yu.security.core.social.qq.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Author yuchl
 * @Date 2020/2/29 0029
 * @Description
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%S";

    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%S&openid=%S";

    private String appid;

    private String openid;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        log.info("accessToken: {}, appid:{}", accessToken, appid);

        String openidUrl = String.format(URL_GET_OPENID, accessToken);

        String openIdResult = getRestTemplate().getForObject(openidUrl, String.class);

        String openid = StringUtils.substringBetween(openIdResult, "openid\":\"", "\"}");
        log.info("openid: {}", openid);
        this.appid = appid;
        this.openid = openid;
    }

    @Override
    public QQUserInfo getUserInfo() {

        String userInfoUrl = String.format(URL_GET_USER_INFO, appid, openid);

        String userInfoResult = getRestTemplate().getForObject(userInfoUrl, String.class);
        log.info("userInfoResult: {}", userInfoResult);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(userInfoResult, QQUserInfo.class);
            qqUserInfo.setOpenId(openid);
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息异常", e);
        }
        return qqUserInfo;
    }
}
