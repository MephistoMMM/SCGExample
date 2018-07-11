package com.rocky.time;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
public class TimeController {

    @RequestMapping("/")
    public String content(@RequestParam(value = "key") String key){
        JSONObject jObj = new JSONObject();
        jObj.put("key",key);
        long currTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        jObj.put("year",sdf.format(currTime));
        sdf = new SimpleDateFormat("MM");
        jObj.put("month",sdf.format(currTime));
        sdf = new SimpleDateFormat("dd");
        jObj.put("day",sdf.format(currTime));
        sdf = new SimpleDateFormat("HH");
        jObj.put("hour",sdf.format(currTime));
        sdf = new SimpleDateFormat("mm");
        jObj.put("minute",sdf.format(currTime));
        sdf = new SimpleDateFormat("ss");
        jObj.put("second",sdf.format(currTime));
        return jObj.toString();
    }

}
