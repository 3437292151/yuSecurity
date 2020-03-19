package com.yu.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yuchl
 * @Date 2020/3/19 0019
 * @Description app认证controller
 */
@RestController
public class AppSecurityController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
}
