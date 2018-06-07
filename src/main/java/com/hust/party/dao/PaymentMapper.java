package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Payment;
import com.hust.party.vo.AccountVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMapper extends BaseMapper<Payment>{
    List<AccountVO> gettotalList(@Param("eid") Integer eid, @Param("page") Page page);
    Integer gettotalListCount(Integer eid);
}