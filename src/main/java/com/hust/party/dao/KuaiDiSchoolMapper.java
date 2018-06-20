package com.hust.party.dao;

import com.hust.party.pojo.KuaiDiSchool;

public interface KuaiDiSchoolMapper  extends BaseMapper<KuaiDiSchool>{

    Integer getSchoolId(String school);
}