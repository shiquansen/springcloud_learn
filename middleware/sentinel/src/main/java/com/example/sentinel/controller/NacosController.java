package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NacosController {


    @SentinelResource(value = "hello1")
    @GetMapping("/hello1")
    public String hello1(){
       return "hello1";
    }


}
