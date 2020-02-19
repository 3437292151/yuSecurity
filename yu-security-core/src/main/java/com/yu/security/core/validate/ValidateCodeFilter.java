package com.yu.security.core.validate;

import com.yu.security.browser.anthentication.YuAuthenticationFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码 验证
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    Logger log = LoggerFactory.getLogger(getClass());

    private YuAuthenticationFailureHandler yuAuthenticationFailureHandler;

    @Override
    public void afterPropertiesSet(){

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        log.info("请求为：{}", requestURI);
        if (requestURI.equals("")){
            try {
                validateCode(new ServletWebRequest(request, response));
                log.info("验证码认证成功！！！！");
            }catch (Exception e){

                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validateCode(ServletWebRequest servletWebRequest) {
    }


}
