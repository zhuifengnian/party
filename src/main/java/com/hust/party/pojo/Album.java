package com.hust.party.pojo;

public class Album {
    private Integer id;

    private Integer userId;

    private String title;

    private String picture;

    public Album(Integer id, Integer userId, String title, String picture) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.picture = picture;
    }

    public Album() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}