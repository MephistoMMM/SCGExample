package com.scg.cloud_discovery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// modify server name
@FeignClient(name= "test-remote")
public interface TestRemote {
    @RequestMapping(value = "/")
    public String content(@RequestParam(value = "key") String key);
}
