package com.ganesh.chat_app_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
