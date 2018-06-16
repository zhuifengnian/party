package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiUserContextMapper;
import com.hust.party.pojo.KuaiDiUserContext;
import com.hust.party.service.KuaidiUserContextService;
import com.hust.party.service.KuaidiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KuaidiUserContextServiceImpl extends  AbstractBaseServiceImpl<KuaiDiUserContext>implements KuaidiUserContextService {

    @Autowired
    private KuaiDiUserContextMapper kuaiDiUserContextMapper;
    @Override
    public BaseMapper<KuaiDiUserContext> getDao() {
        return kuaiDiUserContextMapper;
    }
}
