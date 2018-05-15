package com.hust.party.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Activity {
    private Integer id;

    private Integer enterpriseId;

    private String title;

    private String word;

    private String picture;

    private String video;

    private String feature;

    private Integer containPeople;

    private BigDecimal preferentialPrice;

    private BigDecimal originalPrice;

    private String address;

    private Integer atleastPeople = 1;

    private Date activityTime;

    public Activity(Integer id, Integer enterpriseId, String title, String word, String picture, String video, String feature, Integer containPeople, BigDecimal preferentialPrice, BigDecimal originalPrice, String address, Integer atleastPeople, Date activityTime) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.title = title;
        this.word = word;
        this.picture = picture;
        this.video = video;
        this.feature = feature;
        this.containPeople = containPeople;
        this.preferentialPrice = preferentialPrice;
        this.originalPrice = originalPrice;
        this.address = address;
        this.atleastPeople = atleastPeople;
        this.activityTime = activityTime;
    }

    public Activity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public Integer getContainPeople() {
        return containPeople;
    }

    public void setContainPeople(Integer containPeople) {
        this.containPeople = containPeople;
    }

    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAtleastPeople() {
        return atleastPeople;
    }

    public void setAtleastPeople(Integer atleastPeople) {
        this.atleastPeople = atleastPeople;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }
}