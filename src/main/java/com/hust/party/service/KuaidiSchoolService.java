package com.hust.party.service;

import com.hust.party.pojo.KuaiDiAdmin;
import com.hust.party.pojo.KuaiDiSchool;

import java.util.List;

/**
 * 快递管理员service<br/>
 * fan 2018/6/16 19:16
 */
public interface KuaidiSchoolService extends BaseService<KuaiDiSchool> {
    Integer getSchoolId(String school);
}