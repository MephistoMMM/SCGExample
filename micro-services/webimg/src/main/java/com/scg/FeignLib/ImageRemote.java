package com.scg.FeignLib;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "service-image")
public interface ImageRemote {
    @RequestMapping(value = "/")
    public String content(@RequestParam(value = "key") String key);

}
