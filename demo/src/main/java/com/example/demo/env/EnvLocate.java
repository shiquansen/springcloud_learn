package com.example.demo.env;


import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

public class EnvLocate implements PropertySourceLoader {

    @Override
    public String[] getFileExtensions() {
        return new String[0];
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        return null;
    }
}
