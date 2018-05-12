package com.hust.party.pojo;

public class Activity {
    private Integer id;

    private Integer enterpriseId;

    private String title;

    private String word;

    private String picture;

    private String video;

    private String feature;

    private Integer containPeople;

    private Integer price;

    public Activity(Integer id, Integer enterpriseId, String title, String word, String picture, String video, String feature, Integer containPeople, Integer price) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.title = title;
        this.word = word;
        this.picture = picture;
        this.video = video;
        this.feature = feature;
        this.containPeople = containPeople;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}