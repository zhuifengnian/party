package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiUserMapper;
import com.hust.party.pojo.KuaiDiUser;
import com.hust.party.service.KuaidiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * fan 2018/6/15 7:34
 */
@Service
public class KuaidiUserServiceImpl extends AbstractBaseServiceImpl<KuaiDiUser> implements KuaidiUserService {
    @Autowired
    private KuaiDiUserMapper kuaiDiUserMapper;
    @Override
    public Integer selectUserByOpenId(String open_id) {
        return kuaiDiUserMapper.selectUserByOpenId(open_id);
    }

    @Override
    public KuaiDiUser selectUserInfo(Integer uid) {
        return kuaiDiUserMapper.selectUserInfo(uid);
    }

    @Override
    public BaseMapper<KuaiDiUser> getDao() {
        return kuaiDiUserMapper;
    }
}