package com.hust.party.dao;

import com.hust.party.pojo.RolePerm;

import java.util.List;
import java.util.Map;

public interface RolePermMapper {
    int insert(RolePerm record);

    int insertSelective(RolePerm record);

}