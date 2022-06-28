package com.example.demo;

import com.example.demo.env.importanno.classAo.Imported;
import com.example.demo.env.importanno.test.TestA;
import com.example.demo.env.importanno.test.TestB;
import com.example.demo.proxy.NoProxyBean;
import com.example.demo.proxy.ProxyBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        System.out.println(AopUtils.isAopProxy(run.getBean(ProxyBean.class)));
        System.out.println(AopUtils.isAopProxy(run.getBean(NoProxyBean.class)));

    }

}
