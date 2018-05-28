package com.hust.party.pojo;

public class ActivityTag {
    private Integer id;

    private Integer activityId;

    private String tag;

    public ActivityTag(Integer id, Integer activityId, String tag) {
        this.id = id;
        this.activityId = activityId;
        this.tag = tag;
    }

    public ActivityTag() {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}