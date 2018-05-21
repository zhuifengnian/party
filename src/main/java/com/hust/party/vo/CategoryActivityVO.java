package com.hust.party.vo;

/**
 * 获得类型活动的vo<br/>
 * fan 2018/5/21 23:48
 */
public class CategoryActivityVO {
    private Integer cid;
    private Integer aid;
    private String activityTitle;
    private String word;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}