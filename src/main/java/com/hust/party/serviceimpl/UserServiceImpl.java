package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.UserMapper;
import com.hust.party.pojo.User;
import com.hust.party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/>
 * fan 2018/5/14 14:14
 */
@Service("userService")
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer selectUserByChatId(String open_id) {

        return userMapper.selectUserByChatId(open_id);
    }

    @Override
    public BaseMapper<User> getDao() {
        return userMapper;
    }
}