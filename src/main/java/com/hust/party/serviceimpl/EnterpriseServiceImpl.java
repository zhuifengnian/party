package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.EnterpriseMapper;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * fan 2018/5/14 15:15
 */
@Service
public class EnterpriseServiceImpl extends AbstractBaseServiceImpl<Enterprise> implements EnterpriseService {
    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public BaseMapper<Enterprise> getDao() {
        return enterpriseMapper;
    }
}