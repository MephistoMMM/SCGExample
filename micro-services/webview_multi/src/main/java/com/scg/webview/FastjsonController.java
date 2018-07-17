package com.scg.webview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Kanson on 2018/7/16.
 */
@Controller
@RequestMapping("fastjson")
public class FastjsonController {
    @RequestMapping("test")
    @ResponseBody
    public Weather test() {
        Weather weather = new Weather();

        return weather;
    }

}
