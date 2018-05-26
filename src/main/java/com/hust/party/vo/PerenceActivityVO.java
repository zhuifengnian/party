package com.hust.party.vo;

import com.hust.party.common.Const;

import java.math.BigDecimal;

/**
 * <br/>
 * fan 2018/5/14 14:49
 */
public class PerenceActivityVO {



    private Integer copies;

    private Integer arriveCopies;
    private String enterpriceName;      //商家名
    private String longitude;

    private String latitude;
    private Integer id;

private Integer soldNumber;

    private String title;


    private String picture;


    private Integer containPeople;

    private BigDecimal preferentialPrice;

    private BigDecimal originalPrice;

    private String address;


    public String getEnterpriceName() {
        return enterpriceName;
    }

    public void setEnterpriceName(String enterpriceName) {
        this.enterpriceName = enterpriceName;
    }






    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
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

    public Integer getArriveCopies() {
        return arriveCopies;
    }

    public void setArriveCopies(Integer arriveCopies) {
        this.arriveCopies = arriveCopies;
    }
}