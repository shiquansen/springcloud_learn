package com.example.feign.controller;

import com.example.feign.facede.TransferFacede;

import com.example.producer.facede.FeignFacede;
import org.springframework.beans.factory.annotation.Autowired;
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
