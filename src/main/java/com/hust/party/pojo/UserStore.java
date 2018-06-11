package com.hust.party.pojo;

import java.util.Date;

public class UserStore {
    private Integer id;
    private Integer userId;
    private Integer enterpriseId;
    private Date createTime;

    private Integer state;

    private Date updateTime;

    public UserStore(Integer id, Integer userId,Integer enterpriseId,Integer state,Date createTime ,Date updateTime){
       this.id=id;
       this.enterpriseId=enterpriseId;
       this.userId=userId;
       this.state=state;
       this.createTime=createTime;
       this.updateTime=updateTime;
    }

    public UserStore() {
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

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}