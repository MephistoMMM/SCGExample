package com.wangkecheng.Controller;

import com.wangkecheng.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kanson on 2018/7/12.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/index")
    public String index(Model model)
    {
        Person single = new Person("hyj",21);
        List<Person> people = new ArrayList<Person>();
        Person p1 = new Person("dlp",21);
        Person p2 = new Person("tq",21);
        Person p3 = new Person("mk",21);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);
        return "index";
    }

    @RequestMapping(value = "/showTime")
    public String time(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "CurrentTime";
    }

    @RequestMapping(value = "/getLocation")
    public String location(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        return "Location";
    }

    @RequestMapping(value = "/getWeather")
    public String Weather(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        return "Weather";
    }

}