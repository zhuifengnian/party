package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.PaymentMapper;
import com.hust.party.pojo.OrderUser;
import com.hust.party.pojo.Payment;
import com.hust.party.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luyue on 2018/5/23.
 */
@Service
public class PaymentServiceImpl extends AbstractBaseServiceImpl<Payment>  implements PaymentService {
   @Autowired
   private PaymentMapper paymentMapper;
    @Override
    public BaseMapper<Payment> getDao() {
        return paymentMapper;
    }
}
