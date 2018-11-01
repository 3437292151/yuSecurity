package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2018-7-17.
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] arg){
        /*SpringApplication app = new SpringApplication(DemoApplication.class);
        app.run(arg);*/
        SpringApplication.run(DemoApplication.class,arg);
    }
}
