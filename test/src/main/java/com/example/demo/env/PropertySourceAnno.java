package com.example.demo.env;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application-dev.yml")
public class PropertySourceAnno implements InitializingBean {
    @Value("${home.abc}")
    private String abc;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("PropertySourceAnno: [" + abc+ "]");
    }
}
