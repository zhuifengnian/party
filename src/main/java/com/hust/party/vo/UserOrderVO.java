package com.hust.party.vo;

import com.hust.party.pojo.Order;

import java.util.List;

/**
 * 返回给前台的该用户下的订单<br/>
 * fan 2018/5/14 14:45
 */
public class UserOrderVO {
    List<OrderActivityVO> orders;

    public List<OrderActivityVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderActivityVO> orders) {
        this.orders = orders;
    }
}