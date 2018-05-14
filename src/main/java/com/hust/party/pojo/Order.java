package com.hust.party.pojo;

import org.apache.ibatis.type.Alias;

public class Order {
    private Integer id;

    private Integer activityId;

    public Order(Integer id, Integer activityId) {
        this.id = id;
        this.activityId = activityId;
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
}