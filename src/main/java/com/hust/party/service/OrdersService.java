package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <br/>
 * fan 2018/5/14 13:54
 */
public interface OrdersService extends BaseService<Orders>{
    Integer getOrderId(Integer activityId);
    List<Orders> getOrder(Integer activityId);
    List<Orders> getOrders(Map map);
    int getCount(Map map);
    List<Orders> getNewOrder(Integer activityId);
}