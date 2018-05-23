package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.OrdersMapper;
import com.hust.party.pojo.Orders;
import com.hust.party.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <br/>
 * fan 2018/5/14 14:12
 */
@Service
public class OrdersServiceImpl extends AbstractBaseServiceImpl<Orders>implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public BaseMapper<Orders> getDao() {
        return ordersMapper;
    }

    @Override
    public Integer getOrderId(Integer activityId) {
        return ordersMapper.getOrderId(activityId);
    }

    @Override
    public List<Orders> getOrder(Integer activityId) {
        return ordersMapper.getOrder(activityId);
    }

    @Override
    public List<Orders> getOrders(Map map) {
        return ordersMapper.getOrders( map);
    }

    @Override
    public int getCount(Map map) {
        return ordersMapper.getCount(map);
    }


    @Override
    public List<Orders> getNewOrder(Integer activityId) {
        return ordersMapper.getNewOrder(activityId);
    }



}