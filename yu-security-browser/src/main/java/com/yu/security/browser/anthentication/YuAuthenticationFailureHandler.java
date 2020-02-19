package com.yu.security.browser.anthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.security.core.properties.LoginType;
import com.yu.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-18
 * @Description: 自定义失败处理器
 */
@Component("yuAuthenticationFailureHandler")
public class YuAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登录失败！！！");

        if (securityProperties.getBrowser().getLoginType().equals(LoginType.JSON)){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
            return;
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
