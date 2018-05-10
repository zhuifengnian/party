package com.hust.party.dao;

import com.hust.party.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Map<String, Object>> queryPerm(String userName);

}