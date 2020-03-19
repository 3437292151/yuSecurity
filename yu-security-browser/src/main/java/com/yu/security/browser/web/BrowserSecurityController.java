package com.yu.security.browser.web;

import com.yu.security.core.support.SocialUserInfo;
import com.yu.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-17
 * @Description: 浏览器端认证控制层
 */
@RestController
public class BrowserSecurityController {

    Logger log = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/authentication/require")
    public ResponseEntity<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发重定向的请求：{}", redirectUrl);
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("访问的服务需要身份认证，请引导用户到登录页");
    }

    @GetMapping("/social/me")
    public ResponseEntity<SocialUserInfo> getUser(HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/session/invalid")
    public ResponseEntity<String> sessionInvalid(){
        log.info("sessionInvalid()：/session/invalid");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("session 失效！");
    }
}
