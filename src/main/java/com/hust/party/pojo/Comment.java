package com.hust.party.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer userId;

    private Integer enterpriseId;

    private String context;

    private Date commentTime;

    private Integer score;

    public Comment(Integer id, Integer userId, Integer enterpriseId, String context, Date commentTime, Integer score) {
        this.id = id;
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.context = context;
        this.commentTime = commentTime;
        this.score = score;
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

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}