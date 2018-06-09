package com.hust.party.vo;

import java.util.List;

/**
 * 订单详情页VO<br/>
 * fan 2018/6/9 10:31
 */
public class OrderShareVO {
    OrderActivityVO order;

    List<OrderShareUserVO> users;

    public OrderActivityVO getOrder() {
        return order;
    }

    public void setOrder(OrderActivityVO order) {
        this.order = order;
    }

    public List<OrderShareUserVO> getUsers() {
        return users;
    }

    public void setUsers(List<OrderShareUserVO> users) {
        this.users = users;
    }
}