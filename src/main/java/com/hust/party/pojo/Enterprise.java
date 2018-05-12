package com.hust.party.pojo;

public class Enterprise {
    private Integer id;

    private String chatId;

    private String name;

    private String licence;

    private String username;

    private String card;

    private Integer phone;

    public Enterprise(Integer id, String chatId, String name, String licence, String username, String card, Integer phone) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.licence = licence;
        this.username = username;
        this.card = card;
        this.phone = phone;
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId == null ? null : chatId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence == null ? null : licence.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}