package com.hust.party.vo;

import java.util.Date;

/**
 * Created by luyue on 2018/5/16.
 */
public class KuaidiExpressVo {
 private String name;
 private String pinyinname;

    private String key1;
    private String pinyinkey1;

    private String expressStation;
    private String pinyinexpressStation;

    private Double latitude;

    private Double longitude;

    private String picture;

    private String exactCode;
    private  String text;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }





    public String getExpressStation() {
        return expressStation;
    }

    public void setExpressStation(String expressStation) {
        this.expressStation = expressStation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getExactCode() {
        return exactCode;
    }

    public void setExactCode(String exactCode) {
        this.exactCode = exactCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinname() {
        return pinyinname;
    }

    public void setPinyinname(String pinyinname) {
        this.pinyinname = pinyinname;
    }

    public String getPinyinkey1() {
        return pinyinkey1;
    }

    public void setPinyinkey1(String pinyinkey1) {
        this.pinyinkey1 = pinyinkey1;
    }

    public String getPinyinexpressStation() {
        return pinyinexpressStation;
    }

    public void setPinyinexpressStation(String pinyinexpressStation) {
        this.pinyinexpressStation = pinyinexpressStation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
