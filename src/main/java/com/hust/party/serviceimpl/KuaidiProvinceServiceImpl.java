package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiProvinceMapper;
import com.hust.party.pojo.KuaiDiProvince;
import com.hust.party.service.KuaidiProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/6/20 13:11
 */
@Service
public class KuaidiProvinceServiceImpl extends AbstractBaseServiceImpl<KuaiDiProvince> implements KuaidiProvinceService {

    @Autowired
    private KuaiDiProvinceMapper kuaidiProvinceMapper;
    @Override
    public BaseMapper<KuaiDiProvince> getDao() {
        return kuaidiProvinceMapper;
    }


    @Override
    public List<KuaiDiProvince> selectAllProvince() {
        List<KuaiDiProvince> allProvince = kuaidiProvinceMapper.select(null);
        return allProvince;
    }
}