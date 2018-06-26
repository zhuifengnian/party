package com.hust.party.service;

import com.hust.party.pojo.KuaiDiAdmin;
import com.hust.party.pojo.KuaiDiSchool;
import com.hust.party.vo.KuaidiProvinceAndSchoolVO;

import java.util.List;

/**
 * 快递管理员service<br/>
 * fan 2018/6/16 19:16
 */
public interface KuaidiSchoolService extends BaseService<KuaiDiSchool> {
    Integer getSchoolId(String school);

    /**
     * 根据省份获取其下所有的学校
     * @param provinceId
     * @return
     */
    List<KuaiDiSchool> getSchoolByProvinceId(Integer provinceId);

    /**
     * 获取省份和学校信息
     * @return
     */
    List<KuaidiProvinceAndSchoolVO> getProvinceAndSchool();

}