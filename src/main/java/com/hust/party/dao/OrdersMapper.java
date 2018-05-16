package com.hust.party.dao;

import com.hust.party.pojo.Orders;

public interface OrdersMapper extends BaseMapper<Orders>{
    Integer getOrderId(Integer activityId);
}