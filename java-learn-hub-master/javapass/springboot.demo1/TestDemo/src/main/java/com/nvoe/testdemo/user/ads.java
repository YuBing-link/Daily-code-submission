package com.nvoe.testdemo.user;

public class ads {
    private String pro;
    private Integer tele;

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public Integer getTele() {
        return tele;
    }

    public void setTele(Integer tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "ads{" +
                "pro='" + pro + '\'' +
                ", tele=" + tele +
                '}';
    }
}
