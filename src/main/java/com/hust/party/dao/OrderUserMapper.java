package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.OrderUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderUserMapper  extends BaseMapper<OrderUser>{

    List<Integer> selectOrdersByUid(@Param("uid")Integer uid, @Param("page") Page page);

    /**
     * 根据订单id，返回其下用户数量
     * @param oid
     */
    Integer selectUserCnt(Integer oid);

    /**
     * 根据用户id和订单id，获取用户在那个订单下的情况
     * @param uid
     * @param oid
     */
    OrderUser selectOrderUserByUidAndOid(@Param("uid")Integer uid, @Param("oid")Integer oid);

}