package com.yu.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractSessionStrategy {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String detectedUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;

    public AbstractSessionStrategy(String invalidSessionUrl) {
        this.detectedUrl = invalidSessionUrl;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession();
        String requestURI = request.getRequestURI();
        if(StringUtils.endsWithIgnoreCase(requestURI, ".html")){
            String url = detectedUrl;
            if (StringUtils.endsWithIgnoreCase(detectedUrl, ".html")){
                url = detectedUrl + ".html";
            }
            log.info("session失效,跳转到: {}", url);
            redirectStrategy.sendRedirect(request, response, url);
        }else {
            String message = "session已失效";
            if(isConcurrency()){
                message = message + "，有可能是并发登录导致的";
            }
            log.info("requestURI: {}; message: {}", requestURI, message);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message)));
        }
    }

    /**
     * session失效是否是并发导致的
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }
}
