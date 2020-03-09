package com.yu.security.core.social.weixin.connect;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

@Getter
@Setter
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant(String accessToken,String openId) {
        super(accessToken);
        this.openId = openId;
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
    }


}
