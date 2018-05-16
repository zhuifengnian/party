package com.hust.party.pojo;

public class Orders {
    private Integer id;

    private Integer activityId;

    private Integer state;

    public Orders(Integer id, Integer activityId, Integer state) {
        this.id = id;
        this.activityId = activityId;
        this.state = state;
    }

    public Orders() {
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}