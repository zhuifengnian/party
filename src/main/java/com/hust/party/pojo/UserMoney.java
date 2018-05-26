package com.hust.party.pojo;

import java.math.BigDecimal;

public class UserMoney {
    private Integer id;

    private Integer state;

    private BigDecimal money;

    private Integer userorderId;

    public UserMoney(Integer id, Integer state, BigDecimal money, Integer userorderId) {
        this.id = id;
        this.state = state;
        this.money = money;
        this.userorderId = userorderId;
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

    public Integer getUserorderId() {
        return userorderId;
    }

    public void setUserorderId(Integer userorderId) {
        this.userorderId = userorderId;
    }
}