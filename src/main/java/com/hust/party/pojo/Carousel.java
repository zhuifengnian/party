package com.hust.party.pojo;

public class Carousel {
    private Integer id;

    private String pictureUrl;

    private Integer activityId;

    public Carousel(Integer id, String pictureUrl, Integer activityId) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.activityId = activityId;
    }

    public Carousel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}