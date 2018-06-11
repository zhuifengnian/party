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



    OrderActivityVO getOrderActivityVo(Integer oid);
}