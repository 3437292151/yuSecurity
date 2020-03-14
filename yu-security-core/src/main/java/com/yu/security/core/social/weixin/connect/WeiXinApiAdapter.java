package com.yu.security.core.social.weixin.connect;


import com.yu.security.core.social.weixin.api.WeiXin;
import com.yu.security.core.social.weixin.api.WeiXinUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeiXinApiAdapter implements ApiAdapter<WeiXin> {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String openId;

    public WeiXinApiAdapter() {
    }

    public WeiXinApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo userInfo = api.getUserInfo(openId);
        log.info("userInfo:{}", userInfo);
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {

    }
}
