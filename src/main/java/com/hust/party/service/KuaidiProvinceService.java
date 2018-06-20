package com.hust.party.service;

import com.hust.party.pojo.KuaiDiProvince;

import java.util.List;

/**
 * <br/>
 * fan 2018/6/20 13:09
 */
public interface KuaidiProvinceService extends BaseService<KuaiDiProvince> {

    List<KuaiDiProvince> selectAllProvince();

}