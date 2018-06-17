package com.hust.party.vo;

import java.util.Date;
import java.util.List;

/**
 * 快递登录用户返回数据<br/>
 * fan 2018/6/16 19:40
 */
public class KuaidiLoginUserVO {
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

    //管理员相关
    private boolean hasAdminPrivate;

    private List<Integer> adminPermissionShoolId;

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
        this.nickname = nickname;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
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
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
        this.state = state;
    }

    public boolean isHasAdminPrivate() {
        return hasAdminPrivate;
    }

    public void setHasAdminPrivate(boolean hasAdminPrivate) {
        this.hasAdminPrivate = hasAdminPrivate;
    }

    public List<Integer> getAdminPermissionShoolId() {
        return adminPermissionShoolId;
    }

    public void setAdminPermissionShoolId(List<Integer> adminPermissionShoolId) {
        this.adminPermissionShoolId = adminPermissionShoolId;
    }
}