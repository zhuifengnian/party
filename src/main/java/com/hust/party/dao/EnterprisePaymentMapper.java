package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.vo.AccountVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterprisePaymentMapper extends  BaseMapper<EnterprisePayment> {

}