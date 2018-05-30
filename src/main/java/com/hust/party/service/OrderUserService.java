package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.pojo.OrderUser;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 13:55
 */
public interface OrderUserService extends BaseService<OrderUser> {
    /**
     * 根据用户id来查找订单
     */
    List<Integer> selectOrdersByUid(Integer uid, Page page);

    /**
     * 根据orderid返回其下用户数量
     */
    Integer selectUserCnt(Integer order_id);
    /**
     * 根据用户id和订单id，获取用户在那个订单下的情况,
     * 注意这里只打算查出可以有效的部分，订单取消和商家取消订单是不应该被查出来的
     * @param uid
     * @param oid
     */
    List<OrderUser> selectOrderUserByUidAndOid(Integer uid, Integer oid);

}