package com.rocky.addr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AddrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddrApplication.class, args);
    }
}
