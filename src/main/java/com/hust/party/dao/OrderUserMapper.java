package com.hust.party.dao;

import com.hust.party.pojo.Activity;
import com.hust.party.pojo.OrderUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderUserMapper  extends BaseMapper<OrderUser>{

    List<Integer> selectOrdersByUid(Integer uid);

    /**
     * 根据订单id，返回其下用户数量
     * @param oid
     */
    Integer selectUserCnt(Integer oid);

}