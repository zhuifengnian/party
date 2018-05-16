package com.hust.party.pojo;

public class Order {
    private Integer id;

    private Integer activityId;

    private Integer status;

    public Order(Integer id, Integer activityId, Integer status) {
        this.id = id;
        this.activityId = activityId;
        this.status = status;
    }

    public Order() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}