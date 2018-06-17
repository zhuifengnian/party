package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiAdminMapper;
import com.hust.party.pojo.KuaiDiAdmin;
import com.hust.party.service.KuaidiAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/6/16 19:18
 */
@Service
public class KuaidiAdminServiceImpl extends AbstractBaseServiceImpl<KuaiDiAdmin> implements KuaidiAdminService {
    @Autowired
    private KuaiDiAdminMapper kuaiDiAdminMapper;

    @Override
    public BaseMapper<KuaiDiAdmin> getDao() {
        return kuaiDiAdminMapper;
    }

    @Override
    public List<KuaiDiAdmin> selectByUid(Integer uid) {

        return kuaiDiAdminMapper.selectByUid(uid);
    }
}