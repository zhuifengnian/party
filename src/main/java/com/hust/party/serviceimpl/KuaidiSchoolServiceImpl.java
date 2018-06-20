package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiAdminMapper;
import com.hust.party.dao.KuaiDiSchoolMapper;
import com.hust.party.pojo.KuaiDiAdmin;
import com.hust.party.pojo.KuaiDiSchool;
import com.hust.party.service.KuaidiAdminService;
import com.hust.party.service.KuaidiSchoolService;
import com.hust.party.service.KuaidiSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/6/16 19:18
 */
@Service
public class KuaidiSchoolServiceImpl extends AbstractBaseServiceImpl<KuaiDiSchool> implements KuaidiSchoolService {

@Autowired
private KuaiDiSchoolMapper kuaiDiSchoolMapper;
    @Override
    public BaseMapper<KuaiDiSchool> getDao() {
        return kuaiDiSchoolMapper;
    }


    @Override
    public Integer getSchoolId(String school) {
        return kuaiDiSchoolMapper.getSchoolId(school);
    }
}