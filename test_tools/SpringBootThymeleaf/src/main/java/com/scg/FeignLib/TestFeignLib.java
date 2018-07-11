package com.scg.FeignLib;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class TestFeignLib {
	@Autowired
    TimeRemote time;

	@RequestMapping("/")
	public String content() {
		String v = time.content("TEST");
        
        if(v.equals("Return test value TEST")) {
            return "Success!";
        }else {
            return "Failed! get " + v;
        }
	}

    public static void main(String[] args) {
			SpringApplication.run(TestFeignLib.class, args);
    }
}
