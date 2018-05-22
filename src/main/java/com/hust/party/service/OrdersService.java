package com.hust.party.service;

import com.hust.party.pojo.Orders;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 13:54
 */
public interface OrdersService extends BaseService<Orders>{
    Integer getOrderId(Integer activityId);
    List<Orders> getOrder(Integer activityId);
    List<Orders> getOrders(Integer activityId);
    List<Orders> getNewOrder(Integer activityId);
    List<Orders> getNoOrder(Integer activityId);
    List<Orders> getYOrder(Integer activityId);
    List<Orders> getQOrder(Integer activityId);
}