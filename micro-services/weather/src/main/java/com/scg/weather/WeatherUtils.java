package com.scg.weather;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;


public class WeatherUtils {

    public static String GetWeatherData(String cityname) {
        StringBuilder sb = new StringBuilder();
        try {
            String weather_url = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityname;
            URL url = new URL(weather_url);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            GZIPInputStream gzin = new GZIPInputStream(is);
            InputStreamReader isr = new InputStreamReader(gzin, "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line + " ");
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

