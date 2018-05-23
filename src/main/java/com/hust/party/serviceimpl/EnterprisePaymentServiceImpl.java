package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.EnterprisePaymentMapper;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.service.EnterprisePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luyue on 2018/5/23.
 */
@Service
public class EnterprisePaymentServiceImpl  extends  AbstractBaseServiceImpl<EnterprisePayment>implements EnterprisePaymentService {
 @Autowired
 private EnterprisePaymentMapper enterprisePaymentMapper;
    @Override
    public BaseMapper<EnterprisePayment> getDao() {
        return enterprisePaymentMapper;
    }
}
