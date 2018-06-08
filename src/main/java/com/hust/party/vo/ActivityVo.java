package com.hust.party.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/5/16.
 */
public class ActivityVo {
    private Integer id;
    private String video;
    private String picture;
    private String word;
    private String title;
    private Integer copies;
    private Integer arriveCopies;
    private Integer minuPeople;
    private Integer containPeople;
    private String address;
    private BigDecimal preferentialPrice;
    private BigDecimal originalPrice;
    private Date activityTime;
    private List<String> pictures;
    private List<String> tag;
    private ActivityEnterpriseVo activityEnterpriseVo;
    private List<CommentVo> commentVo;

    private String longitude;

    private String latitude;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getArriveCopies() {
        return arriveCopies;
    }

    public void setArriveCopies(Integer arriveCopies) {
        this.arriveCopies = arriveCopies;
    }

    public Integer getMinuPeople() {
        return minuPeople;
    }

    public void setMinuPeople(Integer minuPeople) {
        this.minuPeople = minuPeople;
    }

    public Integer getContainPeople() {
        return containPeople;
    }

    public void setContainPeople(Integer containPeople) {
        this.containPeople = containPeople;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }



    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }



    public ActivityEnterpriseVo getActivityEnterpriseVo() {
        return activityEnterpriseVo;
    }

    public void setActivityEnterpriseVo(ActivityEnterpriseVo activityEnterpriseVo) {
        this.activityEnterpriseVo = activityEnterpriseVo;
    }




    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }


    public List<CommentVo> getCommentVo() {
        return commentVo;
    }

    public void setCommentVo(List<CommentVo> commentVo) {
        this.commentVo = commentVo;
    }
}
