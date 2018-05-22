package com.hust.party.dao;

import com.hust.party.pojo.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMapper extends BaseMapper<Orders>{
    Integer getOrderId(Integer activityId);
    List<Orders> getOrder(Integer activityId);
    List<Orders> getOrders(Integer activityId);
    List<Orders> getNewOrder(Integer activityId);
    List<Orders> getNoOrder(Integer activityId);
    List<Orders> getYOrder(Integer activityId);
    List<Orders> getQOrder(Integer activityId);
}