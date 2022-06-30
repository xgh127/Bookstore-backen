package com.bookstore.backen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackenApplication {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String home()
    {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackenApplication.class, args);
    }

}
