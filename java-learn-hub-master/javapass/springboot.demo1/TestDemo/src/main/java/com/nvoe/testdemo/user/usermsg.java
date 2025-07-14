package com.nvoe.testdemo.user;

public class usermsg {
    private String name;
    private Integer age;
    private String msg;
    private ads ads;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ads getAds() {
        return ads;
    }

    public void setAds(ads ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "usermsg{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", msg='" + msg + '\'' +
                ", ads=" + ads.toString() +
                '}';
    }
}
