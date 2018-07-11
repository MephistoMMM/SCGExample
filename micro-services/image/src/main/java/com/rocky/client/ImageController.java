package com.rocky.client;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Value("${image.serv}")
    String servName;

    @RequestMapping("/")
    public String content(@RequestParam(value = "key") String key){
        JSONObject jObj = new JSONObject();
        jObj.put("url",servName+key+".jpg");
        return jObj.toString();
    }
}
