package com.example.demo.listen;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author V00000033
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    public void onApplicationEvent(MyEvent event) {
        Object source = event.getSource();
        if(source instanceof  MySource){
            //dosomething
            System.out.println(source);
        }
    }
}
