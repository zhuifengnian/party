package com.hust.party.pojo;

public class Enterprise {
    private Integer id;

    private String nickname;

    private String licence;

    private String leadName;

    private String leadCard;

    private String leadPhone;

    private String leadEmail;

    private Integer categoryId;

    private String leadPosition;

    private String name;

    private String openId;

    private String avatarurl;

    private String city;

    private String province;

    private String country;

    private Integer state;

    public Enterprise(Integer id, String nickname, String licence, String leadName, String leadCard, String leadPhone, String leadEmail, Integer categoryId, String leadPosition, String name, String openId, String avatarurl, String city, String province, String country, Integer state) {
        this.id = id;
        this.nickname = nickname;
        this.licence = licence;
        this.leadName = leadName;
        this.leadCard = leadCard;
        this.leadPhone = leadPhone;
        this.leadEmail = leadEmail;
        this.categoryId = categoryId;
        this.leadPosition = leadPosition;
        this.name = name;
        this.openId = openId;
        this.avatarurl = avatarurl;
        this.city = city;
        this.province = province;
        this.country = country;
        this.state = state;
    }

    public Enterprise() {
        super();
    }

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
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence == null ? null : licence.trim();
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName == null ? null : leadName.trim();
    }

    public String getLeadCard() {
        return leadCard;
    }

    public void setLeadCard(String leadCard) {
        this.leadCard = leadCard == null ? null : leadCard.trim();
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone == null ? null : leadPhone.trim();
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail == null ? null : leadEmail.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getLeadPosition() {
        return leadPosition;
    }

    public void setLeadPosition(String leadPosition) {
        this.leadPosition = leadPosition == null ? null : leadPosition.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}