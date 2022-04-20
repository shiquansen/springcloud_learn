package com.example.producer.facede;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = FeignFacede.FeignfFallBack.class)
public interface FeignFacede {

    @GetMapping("getFeignRpc/{content}")
    public String getFeignRpc(@PathVariable(value = "content") String content);

    class FeignfFallBack implements FeignFacede{
        public String getFeignRpc(String content) {
            return null;
        }
    }
}
