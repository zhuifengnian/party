package com.hust.party.dao;

import com.hust.party.pojo.OrderUser;

public interface OrderUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderUser record);

    int insertSelective(OrderUser record);

    OrderUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderUser record);

    int updateByPrimaryKey(OrderUser record);
}