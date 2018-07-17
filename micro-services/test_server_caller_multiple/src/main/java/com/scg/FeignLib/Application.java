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

    @RequestMapping("/")
    public String content(@RequestParam(value="image", defaultValue="default") String key_image,
                          @RequestParam(value="time", defaultValue="default") String key_time,
                          @RequestParam(value="addr", defaultValue="default") String key_addr,
                          @RequestParam(value="weather", defaultValue="default") String key_weather) {

        String result = "{\n" +
            "\ttime: " + time.content(key_time) + ", \n" +
            "\taddr:" + addr.content(key_addr) + ", \n" +
            "\timage: " + image.content(key_image) + ", \n" +
            "\tweather: " + weather.content(key_weather) + "\n}";

            return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
