package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiExpressMapper;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.service.KuaiDiExpressService;
import com.hust.party.vo.KuaidiExpressVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/6/13.
 */
@Service
public class KuaiDiExpressServiceImpl extends  AbstractBaseServiceImpl<KuaiDiExpress> implements KuaiDiExpressService {
    @Autowired
    private KuaiDiExpressMapper kuaiDiExpressMapper;

    @Override
    public BaseMapper<KuaiDiExpress> getDao() {
        return kuaiDiExpressMapper;
    }


    @Override
    public List<KuaiDiExpress> getListKuaiinfo(Integer school_id,Page page) {
        return kuaiDiExpressMapper.getListKuaiinfo(school_id,page);
    }

    @Override
    public Integer getListKuaiinfoCount(Integer school_id) {
        return kuaiDiExpressMapper.getListKuaiinfoCount(school_id);
    }

    @Override
    public List<KuaiDiExpress> getListKuaiinfo1() {
        return kuaiDiExpressMapper.getListKuaiinfo1();
    }
}
