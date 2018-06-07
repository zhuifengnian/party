package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.PaymentMapper;
import com.hust.party.pojo.OrderUser;
import com.hust.party.pojo.Payment;
import com.hust.party.service.PaymentService;
import com.hust.party.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<AccountVO> gettotalList(Integer eid, Page page) {
        return paymentMapper.gettotalList(eid,page);
    }

    @Override
    public Integer gettotalListCount(Integer eid) {
        return paymentMapper.gettotalListCount(eid);
    }
}
