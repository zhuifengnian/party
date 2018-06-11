package com.hust.party.service;

import com.hust.party.pojo.Orders;
import com.hust.party.vo.OrderShareVO;

/**
 * <br/>
 * fan 2018/5/14 13:54
 */
public interface OrdersService extends BaseService<Orders>{



    /**
     * 获取订单详情页的数据
     * @param oid
     * @return
     */
    OrderShareVO getOrderDetailVO(Integer oid);
}