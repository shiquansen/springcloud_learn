package com.example.demo.proxy;


import org.springframework.stereotype.Component;

@Component
public class ProxyBean {

    public String getHello(){
        return "hello";
    }
}
