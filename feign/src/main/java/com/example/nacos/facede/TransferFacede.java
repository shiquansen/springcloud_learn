package com.example.nacos.facede;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feign-consumer", fallback = TransferFacede.TransferFallback.class)
public interface TransferFacede {

    @GetMapping("transfer/{feignInfo}")
    public String transfer(@PathVariable(value="feignInfo") String feignInfo);

    public class TransferFallback implements TransferFacede{
        @Override
        public String transfer(String feignInfo) {
            return null;
        }
    }
}
