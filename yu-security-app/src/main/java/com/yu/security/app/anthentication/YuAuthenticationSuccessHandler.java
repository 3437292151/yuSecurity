package com.yu.security.app.anthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.security.core.properties.LoginType;
import com.yu.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-18
 * @Description: 自定义成功处理器
 */
@Component("yuAuthenticationSuccessHandler")
public class YuAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功！！");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
            return;
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
