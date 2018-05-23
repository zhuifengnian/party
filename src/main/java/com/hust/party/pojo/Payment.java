package com.hust.party.pojo;

import java.util.Date;

public class Payment {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer state;

    private Integer price;

    private Date creatTime;

    private Integer enterpriseId;

    private Integer activityId;

    public Payment(Integer id, Integer userId, Integer orderId, Integer state, Integer price, Date creatTime, Integer enterpriseId, Integer activityId) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.state = state;
        this.price = price;
        this.creatTime = creatTime;
        this.enterpriseId = enterpriseId;
        this.activityId = activityId;
    }

    public Payment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}