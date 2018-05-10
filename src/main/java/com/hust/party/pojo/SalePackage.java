package com.hust.party.pojo;

/**
 * 套餐信息<br/>
 * fan 2018/5/10 11:55
 */
public class SalePackage {
    private int id;
    private int mId;
    private int phId;
    private int availableTime;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhId() {
        return phId;
    }

    public void setPhId(int phId) {
        this.phId = phId;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}