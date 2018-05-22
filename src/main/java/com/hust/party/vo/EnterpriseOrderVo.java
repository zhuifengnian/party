package com.hust.party.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by luyue on 2018/5/16.
 */
public class EnterpriseOrderVo {
    private Integer id;
    private String title;
    private Date activityTime;
    private BigDecimal preferentialPrice;
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
}
