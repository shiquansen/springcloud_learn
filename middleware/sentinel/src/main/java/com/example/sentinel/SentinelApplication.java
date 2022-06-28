package com.example.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SentinelApplication.class, args);
        System.out.println(run);
    }

}
