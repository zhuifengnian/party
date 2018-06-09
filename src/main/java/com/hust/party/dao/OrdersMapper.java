package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Orders;
import com.hust.party.vo.OrderActivityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrdersMapper extends BaseMapper<Orders>{
    Integer getOrderId(Integer activityId);
    List<Orders> getOrder(Integer activityId);
    List<Orders> getOrders(Map map);
    int getCount(Map map);
    List<Orders> getNewOrder(Integer activityId);
    List<Orders>getActivityOrder(Map map);
    int getActivityCount(Map map);

    OrderActivityVO getOrderActivityVo(Integer oid);
}