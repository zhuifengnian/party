package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.UserForceMapper;
import com.hust.party.pojo.UserForce;
import com.hust.party.service.UserForceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luyue on 2018/6/5.
 */
@Service
public class UserForceServiceImpl extends  AbstractBaseServiceImpl<UserForce> implements UserForceService {
   @Autowired
   private UserForceMapper userForceMapper;
    @Override
    public BaseMapper<UserForce> getDao() {
        return userForceMapper;
    }
}
