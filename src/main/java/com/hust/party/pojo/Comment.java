package com.hust.party.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer userId;

    private Integer enterpriseId;

    private String context;

    private Date time;

    public Comment(Integer id, Integer userId, Integer enterpriseId, String context, Date time) {
        this.id = id;
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.context = context;
        this.time = time;
    }

    public Comment() {
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}