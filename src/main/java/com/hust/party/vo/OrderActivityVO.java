package com.hust.party.vo;

import com.hust.party.common.Const;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <br/>
 * fan 2018/5/14 14:49
 */
public class OrderActivityVO {

    private Integer oid;

    private Integer num;                //人数

    private Integer enid;               //商家id

    private String enterpriceName;      //商家名

    private Integer phone;              //商家电话

    private Integer status = Const.ORDER_STATUS_NOT_FULL;             //订单状态（0可以支付，1拼单人数满，2拼单人数未满，3日期已过）

    private Integer aid;            //活动id

    private Integer enterpriseId;

    private String title;       //活动标题（名称）

    private String word;        //描述

    private String picture;

    private String video;

    private String feature;     //标签

    private Integer containPeople;

    private BigDecimal preferentialPrice;   //优惠价格

    private BigDecimal originalPrice;       //原价

    private String address;

    private Date creatTime;        //下单时间

    private Date activityTime;      //活动时间（消费时间）

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getEnterpriceName() {
        return enterpriceName;
    }

    public void setEnterpriceName(String enterpriceName) {
        this.enterpriceName = enterpriceName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Integer getContainPeople() {
        return containPeople;
    }

    public void setContainPeople(Integer containPeople) {
        this.containPeople = containPeople;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}