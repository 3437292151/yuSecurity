package com.yu.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YuLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String signoutUrl ;

    private ObjectMapper objectMapper = new ObjectMapper();

    public YuLogoutSuccessHandler(String signOutUrl) {
        this.signoutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出SuccessHandler");

        if (StringUtils.isBlank(signoutUrl)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseEntity.ok().body("退出成功")));
        }else {
            response.sendRedirect(signoutUrl);
        }

    }
}
