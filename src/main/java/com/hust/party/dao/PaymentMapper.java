package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Payment;
import com.hust.party.vo.AccountVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMapper extends BaseMapper<Payment>{
    /**获取企业账单列表
     * huoqu
     * @param eid
     * @param page
     * @return
     */
    List<AccountVO> gettotalList(@Param("eid") Integer eid, @Param("page") Page page);
    Integer gettotalListCount(Integer eid);
}