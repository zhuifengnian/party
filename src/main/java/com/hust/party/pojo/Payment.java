package com.hust.party.pojo;

public class Payment {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer status;

    private Integer price;

    public Payment(Integer id, Integer userId, Integer orderId, Integer status, Integer price) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.status = status;
        this.price = price;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}