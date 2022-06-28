package com.example.demo.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestScope;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyScopeConfig implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if(beanFactory instanceof DefaultListableBeanFactory){
            String scopeName = "myScope";
            ((DefaultListableBeanFactory)beanFactory).registerScope(scopeName, new MyScope());
        }

    }
}
