package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.OrdersMapper;
import com.hust.party.pojo.Orders;
import com.hust.party.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}