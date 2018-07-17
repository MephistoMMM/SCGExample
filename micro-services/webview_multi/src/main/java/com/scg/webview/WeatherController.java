package com.scg.webview;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Kanson on 2018/7/16.
 */
@Controller
public class WeatherController {
//    @RequestMapping(value = "/index")
//    public String index(Model model)
//    {
//        return "index";
//        //500error
//    }

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "index";
    }

    @RequestMapping(value = "/w")
    public String weather(){
        WeatherDataUtils weatherDataUtils = new WeatherDataUtils();
        String s = weatherDataUtils.GetWeatherData("杭州");
        System.out.println(s);
        return weatherDataUtils.GetWeatherData("杭州");
    }


    @RequestMapping(value = "/weather")
    public String weather(Map<String, Object> map) {
//        String cityStr = "杭州";
//        String typeStr = "晴";
//        String highStr = "36°C";
//        String lowStr = "27°C";
//        String fengxiangStr = "东南风";


        WeatherDataUtils weatherUtils = new WeatherDataUtils();
        String tqData;
        tqData = weatherUtils.GetWeatherData("杭州");
        JSONObject jsonObject; //主干Json数据
        jsonObject = weatherUtils.StringToJson(tqData);
        String city = jsonObject.getJSONObject("data").getString("city");
        String wendu = jsonObject.getJSONObject("data").getString("wendu");
        JSONArray weathers = jsonObject.getJSONObject("data").getJSONArray("forecast");
        JSONObject weather1 = weathers.getJSONObject(0);

        String date = weather1.getString("date");
        String type = weather1.getString("type");
        String fengxiang  = weather1.getString("fengxiang");
        String fengli  = weather1.getString("fengli");
        String high  = weather1.getString("high");
        String low = weather1.getString("low");

//        System.out.println(city1);
//        System.out.println(wendu);
//        System.out.println(date);
        String cityStr = city;
        String dateStr = date;
        String typeStr = type;
        String wenduStr = wendu;
        String fengxiangStr = fengxiang;
        String fengliStr = fengli.substring(9,13);
        String highStr = high;
        String lowStr = low;

        map.put("City", cityStr);
        map.put("Date", dateStr);
        map.put("Type", typeStr);
        map.put("Wendu",wenduStr);
        map.put("Fengxiang",fengxiangStr);
        map.put("Fengli",fengliStr);
        map.put("High", highStr);
        map.put("Low", lowStr);

        return "weather";
        //500error
    }


    @RequestMapping(value = "/showTime")
    public String time(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "CurrentTime";
    }
}
