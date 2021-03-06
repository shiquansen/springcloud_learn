package com.example.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example")
public class ProducerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProducerApplication.class, args);
        System.out.println(run);
    }
}
