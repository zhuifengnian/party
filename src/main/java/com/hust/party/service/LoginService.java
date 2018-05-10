package com.hust.party.service;

import com.hust.party.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic_Yang on 17/7/6.
 */
public interface LoginService {
    Map queryuser(User user);

    List<Map<String, Object>> queryPerm(String userName);

    List<Map<String, Object>> queryRole(String userName);

}
