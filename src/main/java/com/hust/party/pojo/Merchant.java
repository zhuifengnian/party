package com.hust.party.pojo;

/**
 * 商户信息<br/>
 * fan 2018/5/10 11:35
 */
public class Merchant {
    private int id;
    private String openid;
    private String name;
    private String license;
    private String representerName;
    private String representerIdCard;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getRepresenterName() {
        return representerName;
    }

    public void setRepresenterName(String representerName) {
        this.representerName = representerName;
    }

    public String getRepresenterIdCard() {
        return representerIdCard;
    }

    public void setRepresenterIdCard(String representerIdCard) {
        this.representerIdCard = representerIdCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}