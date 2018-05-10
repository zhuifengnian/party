package com.hust.party.dao;

import com.hust.party.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Map<String, Object>> queryRole(String userName);
}