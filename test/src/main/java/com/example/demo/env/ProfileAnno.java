package com.example.demo.env;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Profile("dev")
//@Component
public class ProfileAnno implements InitializingBean {

    @Value("${home.abc}")
    private String abc;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ProfileAnno: [" +abc +"]");
    }
}
