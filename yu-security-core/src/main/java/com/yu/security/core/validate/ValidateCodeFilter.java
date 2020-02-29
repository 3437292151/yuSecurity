package com.yu.security.core.validate;

import com.yu.security.core.properties.SecurityConstants;
import com.yu.security.core.properties.SecurityProperties;
import com.yu.security.core.validate.image.ImageCode;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码 验证
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationFailureHandler yuAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeHolder validateCodeHolder;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        addUrlMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);

        addUrlMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.SMS);
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
    }

    private void addUrlMap(String urls, ValidateCodeType codeType) {
        if(StringUtils.isNotBlank(urls)){
            String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(urls, ",");
            for (String url : split){
                urlMap.put(url, codeType);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        log.info("请求为：{}", requestURI);

        ValidateCodeType validateCodeType = getValidateCodeType(requestURI);

        if (validateCodeType != null){
            try {
                validateCodeHolder.getValidateCodeProcessor(validateCodeType).validate(new ServletWebRequest(request, response));
                log.info("验证码认证成功！！！！");
            }catch (ValidateCodeException e){
                yuAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private ValidateCodeType getValidateCodeType(String requestURI) {
        ValidateCodeType validateCodeType = null;

        for (String url : urlMap.keySet()){
            log.info("配置url:{}", url);
            if (pathMatcher.match(requestURI, url)){
                validateCodeType = urlMap.get(url);
            }
        }
        return validateCodeType;
    }

}
