package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {


    @GetMapping("/hello")
    public String hello(){
        Entry entry = null;
       try{
           entry = SphU.entry("hello");
           return "hello";
       } catch (BlockException e) {
           throw new RuntimeException("降级处理");
       }finally {
           if (entry != null) {
               entry.exit();
           }
       }
    }
}
