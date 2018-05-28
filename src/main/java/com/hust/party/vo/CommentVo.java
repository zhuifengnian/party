package com.hust.party.vo;

import java.util.Date;

/**
 * Created by luyue on 2018/5/16.
 */
public class CommentVo {
    private String context;

    private String time;

    private Integer score;

    private String username;



    private String avatarurl;



    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }
}
