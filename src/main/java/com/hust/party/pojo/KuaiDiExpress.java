package com.hust.party.pojo;

import java.util.Date;

public class KuaiDiExpress {
    private Integer id;

    private String key1;

    private String name;

    private String expressStation;

    private Double latitude;

    private Double longitude;

    private Date createTime;

    private Date updateTime;

    private String state;

    private String picture;

    public KuaiDiExpress(Integer id, String key1, String name, String expressStation, Double latitude, Double longitude, Date createTime, Date updateTime, String state, String picture) {
        this.id = id;
        this.key1 = key1;
        this.name = name;
        this.expressStation = expressStation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.picture = picture;
    }

    public KuaiDiExpress() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1 == null ? null : key1.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExpressStation() {
        return expressStation;
    }

    public void setExpressStation(String expressStation) {
        this.expressStation = expressStation == null ? null : expressStation.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}