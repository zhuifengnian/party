package com.hust.party.pojo;

public class OrderUser {
    private Integer id;

    private Integer orderId;

    private Integer userId;

    private Integer status;

    public OrderUser(Integer id, Integer orderId, Integer userId, Integer status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}