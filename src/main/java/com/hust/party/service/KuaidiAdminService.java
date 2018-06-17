package com.hust.party.service;

import com.hust.party.pojo.KuaiDiAdmin;

import java.util.List;

/**
 * 快递管理员service<br/>
 * fan 2018/6/16 19:16
 */
public interface KuaidiAdminService extends BaseService<KuaiDiAdmin> {
    /**
     * 判断用户是否是管理员
     * @param uid
     * @return
     */
    List<KuaiDiAdmin> selectByUid(Integer uid);
}