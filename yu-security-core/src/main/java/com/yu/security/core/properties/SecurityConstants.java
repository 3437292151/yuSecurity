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
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认的app社交登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openId";

    /**
     * @Author yuchanglong
     * @Date 2020-2-18
     * @Description 当请求需要身份认证时，默认跳转的url
     **/
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 验证码处理器后缀
     */
    public static final String VALIDATE_CODE_PROCESSOR_SUFFIX= "CodeProcessor";

    /**
     * 验证码发送者后缀
     */
    public static  final String VALIDATE_CODE_SENDER_SUFFIX= "CodeSender";

    /**
     * session失效默认的跳转地址
     */
    public static  final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";
}
