package com.yu.security.core.social.weixin.api;


/**
 * @Author yuchl
 * @Date 2020/2/29 0029
 * @Description QQ
 */
public interface WeiXin {
    WeiXinUserInfo getUserInfo(String openId);
}
