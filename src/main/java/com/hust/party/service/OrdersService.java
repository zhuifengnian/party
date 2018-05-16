package com.hust.party.service;

import com.hust.party.pojo.Orders;

/**
 * <br/>
 * fan 2018/5/14 13:54
 */
public interface OrdersService extends BaseService<Orders>{
    Integer getOrderId(Integer activityId);
}