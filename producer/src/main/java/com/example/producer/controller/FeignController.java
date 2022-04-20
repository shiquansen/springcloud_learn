package com.example.producer.controller;

import com.example.producer.facede.FeignFacede;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeignController implements FeignFacede {

    @GetMapping("getFeignRpc/{content}")
    public String getFeignRpc(@PathVariable(value = "content") String content) {
        System.out.println(content);
        return content;
    }
}
