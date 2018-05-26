package com.hust.party.pojo;

public class ActivityPicture {
    private Integer id;

    private Integer activityId;

    private String pictureUrl;

    private String pictureContext;

    public ActivityPicture(Integer id, Integer activityId, String pictureUrl, String pictureContext) {
        this.id = id;
        this.activityId = activityId;
        this.pictureUrl = pictureUrl;
        this.pictureContext = pictureContext;
    }

    public ActivityPicture() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getPictureContext() {
        return pictureContext;
    }

    public void setPictureContext(String pictureContext) {
        this.pictureContext = pictureContext == null ? null : pictureContext.trim();
    }
}