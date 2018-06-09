package com.hust.party.service;

import com.hust.party.pojo.Orders;
import com.hust.party.vo.OrderShareVO;

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
    List<Orders> getActivityOrder(Map map);
    int getActivityCount(Map map);
    int getCount(Map map);
    List<Orders> getNewOrder(Integer activityId);

    /**
     * 获取订单详情页的数据
     * @param oid
     * @return
     */
    OrderShareVO getOrderDetailVO(Integer oid);
}