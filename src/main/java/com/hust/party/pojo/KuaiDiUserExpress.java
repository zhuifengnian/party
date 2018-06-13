package com.hust.party.pojo;

public class KuaiDiUserExpress {
    private Integer id;

    private Integer userId;

    private Integer expressId;

    private String exactCode;

    private String exactTime;

    private String contactPhone;

    private String state;

    public KuaiDiUserExpress(Integer id, Integer userId, Integer expressId, String exactCode, String exactTime, String contactPhone, String state) {
        this.id = id;
        this.userId = userId;
        this.expressId = expressId;
        this.exactCode = exactCode;
        this.exactTime = exactTime;
        this.contactPhone = contactPhone;
        this.state = state;
    }

    public KuaiDiUserExpress() {
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

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getExactCode() {
        return exactCode;
    }

    public void setExactCode(String exactCode) {
        this.exactCode = exactCode == null ? null : exactCode.trim();
    }

    public String getExactTime() {
        return exactTime;
    }

    public void setExactTime(String exactTime) {
        this.exactTime = exactTime == null ? null : exactTime.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}