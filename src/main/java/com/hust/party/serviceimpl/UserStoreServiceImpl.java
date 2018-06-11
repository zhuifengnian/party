package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.UserStoreMapper;
import com.hust.party.pojo.User;
import com.hust.party.pojo.UserStore;
import com.hust.party.service.UserStoreService;
import com.hust.party.vo.EnterpriseInfoVO;
import com.hust.party.vo.UserStoreEnterpriseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoreServiceImpl  extends AbstractBaseServiceImpl<UserStore> implements UserStoreService {
    @Autowired
    private UserStoreMapper userStoreMapper;
    @Override
    public BaseMapper<UserStore> getDao() {
        return userStoreMapper;
    }

    @Override
    public  List<UserStoreEnterpriseVO>  getUserStore(Integer uid,Page page) {
        return userStoreMapper.getUserStore(uid,page);
    }

    @Override
    public Integer getUserStorecount(Integer uid) {
        return userStoreMapper.getUserStorecount(uid);
    }

    @Override
    public List <UserStore>judgeStore(Integer uid, Integer eid) {
        return userStoreMapper.judgeStore(uid,eid);
    }
}
