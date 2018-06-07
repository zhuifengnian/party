package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.pojo.Payment;
import com.hust.party.vo.AccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 15:12
 */
public interface PaymentService extends BaseService<Payment>{
    List<AccountVO> gettotalList(@Param("eid") Integer eid, @Param("page") Page page);
    Integer gettotalListCount(Integer eid);
}