package com.hust.party.pojo;

import java.util.Date;

public class OrderUser {
    private Integer id;

    private Integer orderId;

    private Integer userId;

    private Integer state;

    private Date creatTime;

    public OrderUser(Integer id, Integer orderId, Integer userId, Integer state, Date creatTime) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.state = state;
        this.creatTime = creatTime;
    }

    public OrderUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}