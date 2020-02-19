package com.yu.security.core.validate;

import com.yu.security.core.validate.service.ValidateCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
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

    private ValidateCodeService validateCodeService;

    @GetMapping("/code/image")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response){
        log.info("创建验证码图片！！！！");
        validateCodeService.createCode(new ServletWebRequest(request, response));
    }
}
