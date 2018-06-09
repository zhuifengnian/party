package com.hust.party.vo;

/**
 * 返回订单下的用户信息<br/>
 * fan 2018/6/9 10:40
 */
public class OrderShareUserVO {
    private Integer uid;

    private String nickname;

    private String avatarurl;

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
}