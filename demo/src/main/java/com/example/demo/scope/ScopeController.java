package com.example.demo.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/scope")
@RestController
public class ScopeController {


    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    void getScope() throws InterruptedException {
        User bean = applicationContext.getBean(User.class);
        new Thread(
                () -> {
                    System.out.println("not in a thread:" + (bean == applicationContext.getBean(User.class)));
                }
        ).start();
        System.out.println("in a thread:" + (bean == applicationContext.getBean(User.class)));
        Thread.sleep(1000L);
    }
}
