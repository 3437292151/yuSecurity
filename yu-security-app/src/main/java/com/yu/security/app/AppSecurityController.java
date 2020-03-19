package com.yu.security.app;

import com.yu.security.app.social.AppSignUpUtils;
import com.yu.security.core.support.SocialUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description app认证controller
 */
@RestController
public class AppSecurityController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @GetMapping("/social/signUp")
    public ResponseEntity<SocialUserInfo> socialSignUp(HttpServletRequest request, HttpServletResponse response){
        log.info("/social/signUp");
        SocialUserInfo userInfo = new SocialUserInfo();
        ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(servletWebRequest);
        appSignUpUtils.saveConnectionData(servletWebRequest, connection.createData());
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userInfo);
    }


}
