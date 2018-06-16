package com.hust.party.vo;

import java.util.Date;

/**
 * Created by luyue on 2018/5/16.
 */
public class KuaidiExpressVo {
 private String expressCompany;
 private String expressCompant_E;

    private String landmark;
    private String lankmark_E;

    private String expressStation;
    private String expressStation_E;

    private Double latitude;

    private Double longitude;

    private String picture;

    private String extractCode;
    private  Integer state;

    private String picture2;




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








    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressCompant_E() {
        return expressCompant_E;
    }

    public void setExpressCompant_E(String expressCompant_E) {
        this.expressCompant_E = expressCompant_E;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLankmark_E() {
        return lankmark_E;
    }

    public void setLankmark_E(String lankmark_E) {
        this.lankmark_E = lankmark_E;
    }

    public String getExpressStation_E() {
        return expressStation_E;
    }

    public void setExpressStation_E(String expressStation_E) {
        this.expressStation_E = expressStation_E;
    }

    public String getExtractCode() {
        return extractCode;
    }

    public void setExtractCode(String extractCode) {
        this.extractCode = extractCode;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }
}
