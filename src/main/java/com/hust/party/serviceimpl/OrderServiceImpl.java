package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.OrderMapper;
import com.hust.party.pojo.Order;
import com.hust.party.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * fan 2018/5/14 14:12
 */
@Service
public class OrderServiceImpl extends AbstractBaseServiceImpl<Order>implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public BaseMapper<Order> getDao() {
        return orderMapper;
    }
}