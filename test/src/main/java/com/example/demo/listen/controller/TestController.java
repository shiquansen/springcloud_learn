package com.example.demo.listen.controller;

import com.example.demo.listen.MyEvent;
import com.example.demo.listen.MySource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController  {


    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/test")
    public String getHelloworld(@RequestParam("id") String hello){
        applicationContext.publishEvent(new MyEvent(new MySource(1, hello)));
        return hello;
    }
}
