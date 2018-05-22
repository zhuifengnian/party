package com.hust.party.pojo;

import java.util.Date;

public class Orders {
    private Integer id;

    private Integer activityId;

    private Integer state;

    private Date createTime;

    private Date finishTime;

    public Orders(Integer id, Integer activityId, Integer state, Date createTime, Date finishTime) {
        this.id = id;
        this.activityId = activityId;
        this.state = state;
        this.createTime = createTime;
        this.finishTime = finishTime;
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
}