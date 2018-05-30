package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.OrderUserMapper;
import com.hust.party.pojo.OrderUser;
import com.hust.party.service.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 14:13
 */
@Service
public class OrderUserServiceImpl extends AbstractBaseServiceImpl<OrderUser> implements OrderUserService {

    @Autowired
    private OrderUserMapper orderUserMapper;
    @Override
    public BaseMapper<OrderUser> getDao() {
        return orderUserMapper;
    }

    @Override
    public List<Integer> selectOrdersByUid(Integer uid, Page page) {

        return orderUserMapper.selectOrdersByUid(uid, page);
    }

    @Override
    public Integer selectUserCnt(Integer order_id) {

        return orderUserMapper.selectUserCnt(order_id);
    }

    @Override
    public List<OrderUser> selectOrderUserByUidAndOid(Integer uid, Integer oid) {
        return orderUserMapper.selectOrderUserByUidAndOid(uid, oid);
    }


}