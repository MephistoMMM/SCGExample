package com.scg.webview;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class WeatherDataUtils {

    public static String GetWeatherData(String cityname) {
        String result = "";
        BufferedReader read = null;


        try {
            URL realurl = new URL("http://localhost:8786/weather");
            URLConnection connection = realurl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(result);

        return result;

    }

    public JSONObject StringToJson(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject;

    }

}
