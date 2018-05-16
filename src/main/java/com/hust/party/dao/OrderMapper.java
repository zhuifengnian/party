package com.hust.party.dao;

import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper  extends BaseMapper<Order>{
         Integer getOrderId(Integer activityId);
}