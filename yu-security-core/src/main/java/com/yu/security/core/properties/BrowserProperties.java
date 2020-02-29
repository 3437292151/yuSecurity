package com.yu.security.core.properties;

import lombok.Data;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-18
 * @Description: 浏览器端参数
 */
@Data
public class BrowserProperties {

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private Integer rememberMeSeconds = 3600*24*7;

}
