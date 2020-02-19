package com.yu.security.core.properties;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-18
 * @Description: 常量池
 */
public interface SecurityConstants {

    /**
     * @Author yuchanglong
     * @Date 2020-2-18
     * @Description  默认登录页面
     **/
    public static final String DEFAULT_LOGIN_PAGE_URL = "/login.html";

    /**
     * @Author yuchanglong
     * @Date 2020-2-18
     * @Description 默认的用户名密码登录请求处理url
     **/
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/auth";


    /**
     * @Author yuchanglong
     * @Date 2020-2-18
     * @Description 当请求需要身份认证时，默认跳转的url
     **/
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
}
