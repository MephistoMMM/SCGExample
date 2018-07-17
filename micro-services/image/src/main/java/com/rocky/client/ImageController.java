package com.rocky.client;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {
    @Value("${image.serv}")
    String servName;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    Registration registration;

    @RequestMapping("/")
    public String content(@RequestParam(value = "key") String key){
        JSONObject jObj = new JSONObject();

        final List<ServiceInstance> instances = discoveryClient.getInstances( registration.getServiceId() );

        jObj.put("host", instances.get(0).getHost());
        jObj.put("metadata",instances.get(0).getMetadata());
        jObj.put("url",servName+key+".jpg");
        return jObj.toString();
    }
}
