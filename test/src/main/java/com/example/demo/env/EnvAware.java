package com.example.demo.env;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvAware implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment);
    }
}
