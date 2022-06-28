package com.example.demo.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

public class MyEnvPP implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            //before可以做一些环境的判断， 或者当前用户的判断，或者容器的判断选择定制化加载配置
            Properties properties = PropertiesLoaderUtils.loadAllProperties("file.properties");
            PropertiesPropertySource source = new PropertiesPropertySource("propertySourceName", properties);
            environment.getPropertySources().addFirst(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
