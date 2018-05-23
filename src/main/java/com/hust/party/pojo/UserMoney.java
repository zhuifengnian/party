package com.hust.party.pojo;

import java.math.BigDecimal;

public class UserMoney {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer state;

    private BigDecimal money;

    public UserMoney(Integer id, Integer userId, Integer orderId, Integer state, BigDecimal money) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.state = state;
        this.money = money;
    }

    public UserMoney() {
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}