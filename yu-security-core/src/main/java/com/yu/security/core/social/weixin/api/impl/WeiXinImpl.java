package com.yu.security.core.social.weixin.api.impl;


import com.yu.security.core.social.weixin.api.WeiXin;
import com.yu.security.core.social.weixin.api.WeiXinUserInfo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%S&openid=%S&lang=zh_CN";

    private String appid;

    private ObjectMapper objectMapper = new ObjectMapper();

    public WeiXinImpl(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid = appid;
    }

    @Override
    public WeiXinUserInfo getUserInfo(String openId) {

        String userInfoUrl = String.format(URL_GET_USER_INFO, appid, openId);

        String userInfoResult = getRestTemplate().getForObject(userInfoUrl, String.class);
        log.info("userInfoResult: {}", userInfoResult);
        WeiXinUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(userInfoResult, WeiXinUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息异常", e);
        }
        return qqUserInfo;
    }
}
