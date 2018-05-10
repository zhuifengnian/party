package com.hust.party.pojo;

/**
 * 轰趴馆信息<br/>
 * fan 2018/5/10 11:50
 */
public class PartyHouse {
    private int id;
    private int mId;
    private String title;
    private String textDescribe;
    private String pictureDescribe;
    private String videoDescribe;
    private String otherFeature;
    private int capacity;
    private int sellNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextDescribe() {
        return textDescribe;
    }

    public void setTextDescribe(String textDescribe) {
        this.textDescribe = textDescribe;
    }

    public String getPictureDescribe() {
        return pictureDescribe;
    }

    public void setPictureDescribe(String pictureDescribe) {
        this.pictureDescribe = pictureDescribe;
    }

    public String getVideoDescribe() {
        return videoDescribe;
    }

    public void setVideoDescribe(String videoDescribe) {
        this.videoDescribe = videoDescribe;
    }

    public String getOtherFeature() {
        return otherFeature;
    }

    public void setOtherFeature(String otherFeature) {
        this.otherFeature = otherFeature;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }
}