package com.example.my_baken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)


public class MyBakenApplication {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String home(){
        return "Hello World!" ;
    }
    public static void main(String[] args) {
        SpringApplication.run(MyBakenApplication.class, args);
    }

}
