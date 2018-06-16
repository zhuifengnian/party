package com.hust.party.pojo;

import java.util.Date;

public class KuaiDiAdmin {
    private Integer id;

    private Integer userId;

    private Integer schoolId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public KuaiDiAdmin(Integer id, Integer userId, Integer schoolId, Integer state, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.schoolId = schoolId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public KuaiDiAdmin() {
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}