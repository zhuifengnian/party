package com.hust.party.service;

import com.hust.party.pojo.Order;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 13:54
 */
public interface OrderService extends BaseService<Order>{
    Integer getOrderId(Integer activityId);
}