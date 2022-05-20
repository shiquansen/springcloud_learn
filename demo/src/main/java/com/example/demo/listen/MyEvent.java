package com.example.demo.listen;

import org.springframework.context.ApplicationEvent;

/**
 * @author V00000033
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
