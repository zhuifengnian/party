package com.hust.party.pojo;

public class KuaiDiUserContext {
    private Integer id;

    private Integer userId;

    private String userPhone;

    private String userContext;

    private String contextClassify;

    public KuaiDiUserContext(Integer id, Integer userId, String userPhone, String userContext, String contextClassify) {
        this.id = id;
        this.userId = userId;
        this.userPhone = userPhone;
        this.userContext = userContext;
        this.contextClassify = contextClassify;
    }

    public KuaiDiUserContext() {
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserContext() {
        return userContext;
    }

    public void setUserContext(String userContext) {
        this.userContext = userContext == null ? null : userContext.trim();
    }

    public String getContextClassify() {
        return contextClassify;
    }

    public void setContextClassify(String contextClassify) {
        this.contextClassify = contextClassify == null ? null : contextClassify.trim();
    }
}