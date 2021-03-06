package com.hust.party.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Activity {
    private Integer id;

    private Integer enterpriseId;

    private String title;

    private String picture;

    private String video;

    private String feature;

    private Integer containPeople;

    private BigDecimal preferentialPrice;

    private BigDecimal originalPrice;

    private String address;

    private Date activityTime;

    private Integer minuPeople;

    private String addressName;

    private String longitude;

    private String latitude;

    private String favourable;

    private Integer classify;

    private Date arriveTime;

    private Integer category;

    private Integer copies;

    private Integer arriveCopies;

    private Integer state;

    private String word;

    public Activity(Integer id, Integer enterpriseId, String title, String picture, String video, String feature, Integer containPeople, BigDecimal preferentialPrice, BigDecimal originalPrice, String address, Date activityTime, Integer minuPeople, String addressName, String longitude, String latitude, String favourable, Integer classify, Date arriveTime, Integer category, Integer copies, Integer arriveCopies, Integer state, String word) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.title = title;
        this.picture = picture;
        this.video = video;
        this.feature = feature;
        this.containPeople = containPeople;
        this.preferentialPrice = preferentialPrice;
        this.originalPrice = originalPrice;
        this.address = address;
        this.activityTime = activityTime;
        this.minuPeople = minuPeople;
        this.addressName = addressName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.favourable = favourable;
        this.classify = classify;
        this.arriveTime = arriveTime;
        this.category = category;
        this.copies = copies;
        this.arriveCopies = arriveCopies;
        this.state = state;
        this.word = word;
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

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getMinuPeople() {
        return minuPeople;
    }

    public void setMinuPeople(Integer minuPeople) {
        this.minuPeople = minuPeople;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getFavourable() {
        return favourable;
    }

    public void setFavourable(String favourable) {
        this.favourable = favourable == null ? null : favourable.trim();
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getArriveCopies() {
        return arriveCopies;
    }

    public void setArriveCopies(Integer arriveCopies) {
        this.arriveCopies = arriveCopies;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }
}