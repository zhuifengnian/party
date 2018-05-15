package com.hust.party.pojo;

public class Favourable {
    private Integer id;

    private Integer reachPrice;

    private Integer favourablePrice;

    private String context;

    public Favourable(Integer id, Integer reachPrice, Integer favourablePrice, String context) {
        this.id = id;
        this.reachPrice = reachPrice;
        this.favourablePrice = favourablePrice;
        this.context = context;
    }

    public Favourable() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReachPrice() {
        return reachPrice;
    }

    public void setReachPrice(Integer reachPrice) {
        this.reachPrice = reachPrice;
    }

    public Integer getFavourablePrice() {
        return favourablePrice;
    }

    public void setFavourablePrice(Integer favourablePrice) {
        this.favourablePrice = favourablePrice;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}