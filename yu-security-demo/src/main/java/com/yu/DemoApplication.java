package com.yu;

import com.yu.util.AesException;
import com.yu.util.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2018-7-17.
 */
@SpringBootApplication
@RestController
public class DemoApplication {

    private Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] arg){
        /*SpringApplication app = new SpringApplication(DemoApplication.class);
        app.run(arg);*/
        SpringApplication.run(DemoApplication.class,arg);
    }

    /*@GetMapping("/")
    public String getToken(String signature, String timestamp,
                            String nonce, String echostr, HttpServletResponse response) throws IOException {
        log.info("signature: {};timestamp: {};nonce: {};echostr: {}",
                signature,
                timestamp,
                nonce,
                echostr);
        String token="f7d4745253a499f49e243c4673fe1d81";
        String jiami="";
        try {
            jiami=SHA1.getSHA1(token, timestamp, nonce,"");//这里是对三个参数进行加密
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("加密"+jiami);
        System.out.println("本身"+signature);


        return echostr;
    }*/
}
