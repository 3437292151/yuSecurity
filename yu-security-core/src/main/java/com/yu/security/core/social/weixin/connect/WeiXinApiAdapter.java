package com.yu.security.core.social.weixin.connect;


import com.yu.security.core.social.weixin.api.WeiXin;
import com.yu.security.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeiXinApiAdapter implements ApiAdapter<WeiXin> {

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
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {

    }
}
