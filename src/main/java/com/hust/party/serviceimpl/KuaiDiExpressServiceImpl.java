package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiExpressMapper;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.service.KuaiDiExpressService;
import com.hust.party.vo.KuaidiExpressVo;
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
    public List<KuaiDiExpress> getListKuaiinfo(String express_station) {
        return kuaiDiExpressMapper.getListKuaiinfo(express_station);
    }

    @Override
    public List<KuaiDiExpress> getListKuaiinfo1() {
        return kuaiDiExpressMapper.getListKuaiinfo1();
    }
}
