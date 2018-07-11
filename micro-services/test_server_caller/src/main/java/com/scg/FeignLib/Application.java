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

    @RequestMapping("/{name}/")
    public String content(@PathVariable("name") String name,
                          @RequestParam(value="key", defaultValue="default") String key) {
        if(name.equals("time")) {
            return time.content(key);
        } else if (name.equals("addr")) {
            return addr.content(key);
        } else if (name.equals("image")) {
            return image.content(key);
        } else if (name.equals("weather")) {
            return weather.content(key);
        }

        throw new ServiceNotFoundException();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
