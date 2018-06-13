package com.hust.party.pojo;

import java.util.Date;

public class KuaiDiUser {
    private Integer id;

    private String nickname;

    private String avatarurl;

    private Integer gender;

    private String email;

    private String phone;

    private String openId;

    private Date createTime;

    private Date updateTime;

    private String state;

    public KuaiDiUser(Integer id, String nickname, String avatarurl, Integer gender, String email, String phone, String openId, Date createTime, Date updateTime, String state) {
        this.id = id;
        this.nickname = nickname;
        this.avatarurl = avatarurl;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.openId = openId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    public KuaiDiUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}