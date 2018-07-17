package com.scg.FeignLib;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class Application {
    @Autowired
    TimeRemote time;
    @Autowired
    AddrRemote addr;
    @Autowired
    ImageRemote image;
    @Autowired
    WeatherRemote weather;

    @RequestMapping("/")
    public String content(@RequestParam(value = "key", defaultValue="default") String key){
        String imageStr = image.content(key);
        ImageBean imgbean = JSON.parseObject (imageStr, ImageBean.class);
        return "<!DOCTYPE html> <html> <head> <title>Image</title> </head> <body> <img src=\""+imgbean.getUrl()+" \" /> </body> </html> ";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
