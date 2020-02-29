package com.yu.service;

import com.yu.security.core.validate.image.ImageCode;
import com.yu.security.core.validate.ValidateCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

//@Component("imageCodeGenerate")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    Logger log = LoggerFactory.getLogger(getClass());

    public void createCode(ServletWebRequest servletWebRequest) throws IOException {
        log.info("DemoImageCodeGenerator");
        ServletOutputStream outputStream = servletWebRequest.getResponse().getOutputStream();

        outputStream.write("DemoImageCodeGenerator".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public ImageCode generate(ServletWebRequest servletWebRequest) {
        return null;
    }
}
