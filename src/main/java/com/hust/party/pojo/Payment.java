package com.hust.party.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private Integer id;

    private Integer orderId;

    private Integer state;

    private BigDecimal price;

    private Date createTime;

    private Integer enterpriseId;

    private Integer activityId;

    public Payment(Integer id, Integer orderId, Integer state, BigDecimal price, Date createTime, Integer enterpriseId, Integer activityId) {
        this.id = id;
        this.orderId = orderId;
        this.state = state;
        this.price = price;
        this.createTime = createTime;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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