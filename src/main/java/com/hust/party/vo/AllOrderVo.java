package com.hust.party.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by luyue on 2018/5/16.
 */
public class AllOrderVo {
    private Integer id;
    private String title;
    private Date activityTime;
    private Integer state;
    private Integer minuPeople;
    private BigDecimal preferentialPrice;
    private Date createTime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getMinuPeople() {
        return minuPeople;
    }

    public void setMinuPeople(Integer minuPeople) {
        this.minuPeople = minuPeople;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
