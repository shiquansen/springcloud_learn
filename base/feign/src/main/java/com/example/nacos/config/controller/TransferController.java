package com.example.nacos.config.controller;

import com.example.nacos.facede.TransferFacede;

import com.example.producer.facede.FeignFacede;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TransferController implements TransferFacede {


    @Resource
    private FeignFacede feignFacede;

    @Override
    @GetMapping("transfer/{feignInfo}")
    public String transfer(@PathVariable(value="feignInfo") String feignInfo){
        String feignRpc = feignFacede.getFeignRpc("hello " + feignInfo);
        return feignRpc;
    }
}
