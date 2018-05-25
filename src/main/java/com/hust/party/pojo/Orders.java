package com.hust.party.pojo;

import java.util.Date;

public class Orders {
    private Integer id;

    private Integer activityId;

    private Integer state;

    private Date createTime;

    private Date finishTime;

    private Integer enterpriseId;

    private Date activityTime;

    private String qrCode;

    public Orders(Integer id, Integer activityId, Integer state, Date createTime, Date finishTime, Integer enterpriseId, Date activityTime, String qrCode) {
        this.id = id;
        this.activityId = activityId;
        this.state = state;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.enterpriseId = enterpriseId;
        this.activityTime = activityTime;
        this.qrCode = qrCode;
    }

    public Orders() {
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }
}