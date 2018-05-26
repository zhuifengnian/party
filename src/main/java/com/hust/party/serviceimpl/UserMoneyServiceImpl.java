package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.UserMoneyMapper;
import com.hust.party.pojo.UserMoney;
import com.hust.party.service.UserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/24 14:23
 */
@Service
public class UserMoneyServiceImpl extends AbstractBaseServiceImpl<UserMoney> implements UserMoneyService {

    @Autowired
    private UserMoneyMapper userMoneyMapper;

    @Override
    public BaseMapper<UserMoney> getDao() {
        return userMoneyMapper;
    }


}