package com.yu.security.core.validate;

import com.yu.security.core.properties.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yuchanglong
 * @Date: 2020-2-19
 * @Description: 验证码 控制层
 */
@RestController
public class ValidateCodeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ValidateCodeHolder validateCodeHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) {
        log.info(type + "创建验证码！！！！");
        validateCodeHolder.getValidateCodeProcessor(type).createCode(new ServletWebRequest(request, response));
    }

}
