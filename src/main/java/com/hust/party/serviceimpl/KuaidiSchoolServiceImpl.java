package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.KuaiDiAdminMapper;
import com.hust.party.dao.KuaiDiSchoolMapper;
import com.hust.party.pojo.KuaiDiAdmin;
import com.hust.party.pojo.KuaiDiProvince;
import com.hust.party.pojo.KuaiDiSchool;
import com.hust.party.service.KuaidiAdminService;
import com.hust.party.service.KuaidiProvinceService;
import com.hust.party.service.KuaidiSchoolService;
import com.hust.party.service.KuaidiSmsService;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.KuaidiProvinceAndSchoolVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <br/>
 * fan 2018/6/16 19:18
 */
@Service
public class KuaidiSchoolServiceImpl extends AbstractBaseServiceImpl<KuaiDiSchool> implements KuaidiSchoolService {

    @Autowired
    private KuaiDiSchoolMapper kuaiDiSchoolMapper;
    @Autowired
    private KuaidiProvinceService kuaidiProvinceService;
    @Override
    public BaseMapper<KuaiDiSchool> getDao() {
        return kuaiDiSchoolMapper;
    }


    @Override
    public Integer getSchoolId(String school) {
        return kuaiDiSchoolMapper.getSchoolId(school);
    }

    @Override
    public List<KuaiDiSchool> getSchoolByProvinceId(Integer provinceId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("provinceId", provinceId);
        List<KuaiDiSchool> schools = kuaiDiSchoolMapper.select(map);
        return schools;
    }

    @Override
    public List<KuaidiProvinceAndSchoolVO> getProvinceAndSchool() {
        List<KuaidiProvinceAndSchoolVO> kuaidiProvinceAndSchoolVOs = new ArrayList<>();
        //先拿到所有学校
        List<KuaiDiSchool> allSchools = kuaiDiSchoolMapper.select(null);
        //在拿到学校下所对应的省份
        for (KuaiDiSchool school: allSchools) {
            //封装数据到vo中
            KuaidiProvinceAndSchoolVO kuaidiProvinceAndSchoolVO = new KuaidiProvinceAndSchoolVO();

            ReflectUtil.copyProperties(kuaidiProvinceAndSchoolVO, school);
            kuaidiProvinceAndSchoolVO.setSchoolId(school.getId());

            KuaiDiProvince kuaiDiProvince = kuaidiProvinceService.selectByPrimaryKey(school.getProvinceId());
            ReflectUtil.copyProperties(kuaidiProvinceAndSchoolVO, kuaiDiProvince);
            kuaidiProvinceAndSchoolVO.setProvinceId(kuaiDiProvince.getId());

            kuaidiProvinceAndSchoolVOs.add(kuaidiProvinceAndSchoolVO);
        }
        return kuaidiProvinceAndSchoolVOs;
    }
}