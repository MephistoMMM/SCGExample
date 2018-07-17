package com.rocky.addr;

import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;

@RestController
public class AddrController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    Registration registration;



    public String getAddresses(String content, String encodingString) {
        // 这里调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
            }

            return returnStr;
        }
        return null;
    }
    public String getIpAddr(javax.servlet.http.HttpServletRequest request) {
        String ip = request.getHeader(("X-IP"));
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-forwarded-for");

        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }
    public String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    @RequestMapping("/")
    public String content(@RequestParam(value = "key") String key){
        String ip = key;
        AddrBean addrBean = new AddrBean();
        addrBean.setIp(ip);
        JSONObject jObj = JSONObject.fromObject(getAddresses("ip="+ip, "utf-8"));

        addrBean.setCountry(jObj.getJSONObject("data").getString("country"));
        addrBean.setRegion(jObj.getJSONObject("data").getString("region"));
        addrBean.setCity(jObj.getJSONObject("data").getString("city"));
        JSONObject retObj= JSONObject.fromObject(addrBean);


        final List<ServiceInstance> instances = discoveryClient.getInstances( registration.getServiceId() );
        retObj.put("host",instances.get(0).getHost());
        retObj.put("metadata",instances.get(0).getMetadata());
        return retObj.toString();
    }
}
