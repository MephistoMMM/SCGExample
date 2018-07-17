package com.rocky.addr;

public class AddrBean {
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {

        this.country = country;
        if(country.equals("XX"))
            this.country = "本地";
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
        if(region.equals("XX"))
            this.region = "本地";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        if(city.equals("XX"))
            this.city = "本地";
    }
    private String ip;
    private String country;
    private String region;
    private String city;
}
