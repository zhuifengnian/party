package com.hust.party.pojo;

public class UserWallet {
    private Integer id;

    private Integer userId;

    private Integer userWallet;

    public UserWallet(Integer id, Integer userId, Integer userWallet) {
        this.id = id;
        this.userId = userId;
        this.userWallet = userWallet;
    }

    public UserWallet() {
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

    public Integer getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Integer userWallet) {
        this.userWallet = userWallet;
    }
}