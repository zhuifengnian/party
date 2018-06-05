package com.hust.party.pojo;

import java.util.Date;

public class UserForce {
    private Integer id;

    private Integer userId;

    private Integer gold;

    private Integer userForce;

    private String userMedal;

    private Integer state;

    private Date createTime;

    private Integer timeState;

    private Date updateTime;

    public UserForce(Integer id, Integer userId, Integer gold, Integer userForce, String userMedal, Integer state, Date createTime, Integer timeState, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.gold = gold;
        this.userForce = userForce;
        this.userMedal = userMedal;
        this.state = state;
        this.createTime = createTime;
        this.timeState = timeState;
        this.updateTime = updateTime;
    }

    public UserForce() {
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

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getUserForce() {
        return userForce;
    }

    public void setUserForce(Integer userForce) {
        this.userForce = userForce;
    }

    public String getUserMedal() {
        return userMedal;
    }

    public void setUserMedal(String userMedal) {
        this.userMedal = userMedal == null ? null : userMedal.trim();
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

    public Integer getTimeState() {
        return timeState;
    }

    public void setTimeState(Integer timeState) {
        this.timeState = timeState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}