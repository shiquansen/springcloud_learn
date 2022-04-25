package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnnotationController {


    @SentinelResource(value = "helloworld_springcloud")
    @GetMapping("/hello3")
    public String hello1(){
       return "hello3";
    }


}
