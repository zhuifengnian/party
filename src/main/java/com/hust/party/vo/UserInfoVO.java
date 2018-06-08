package com.hust.party.vo;

/**
 * 用户数据信息vo<br/>
 * fan 2018/6/8 11:33
 */
public class UserInfoVO {
    private Integer uid;

    private String nickname;

    private String avatarurl;

    private Integer gender;

    private Integer gold;

    private Integer userForce;

    private String userMedal;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
        this.userMedal = userMedal;
    }
}