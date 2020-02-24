package com.yu.security.core.validate;

import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码 验证
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private final static String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationFailureHandler yuAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private Set<String> urls = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String imageUrl = securityProperties.getCode().getImage().getUrls();
        String[] split = imageUrl.split(",");
        for (String url : split){
            urls.add(url);
        }
        urls.add(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        log.info("请求为：{}", requestURI);

        boolean flag = false;

        for (String url : urls){
            log.info("配置url:{}", url);
            if (pathMatcher.match(requestURI, url)){
                flag = true;
            }
        }

        if (flag){
            try {
                validateCode(new ServletWebRequest(request, response));
                log.info("验证码认证成功！！！！");
            }catch (ValidateCodeException e){
                yuAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }



    private void validateCode(ServletWebRequest servletWebRequest) {

        ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(servletWebRequest, SESSION_KEY);

        String validateCode ;
        try {
            validateCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(validateCode)){
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), validateCode)){
            sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);

    }


}
