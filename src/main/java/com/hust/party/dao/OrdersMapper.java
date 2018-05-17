package com.hust.party.dao;

import com.hust.party.pojo.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper extends BaseMapper<Orders>{
    Integer getOrderId(Integer activityId);
}