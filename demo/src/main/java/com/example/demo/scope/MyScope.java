package com.example.demo.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author V00000033
 */
public class MyScope implements Scope {
    private final ThreadLocal<Map<String, Object>> threadLoacal = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> scope = threadLoacal.get();
        Object obj = scope.get(name);
        // 不存在则放入ThreadLocal
        if (obj == null) {
            obj = objectFactory.getObject();
            scope.put(name, obj);
        }
        return obj;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> scope = threadLoacal.get();
        return scope.remove(name);
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(String arg0, Runnable arg1) {
    }

    @Override
    public Object resolveContextualObject(String arg0) {
        return null;
    }

}