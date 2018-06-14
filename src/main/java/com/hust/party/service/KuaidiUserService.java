package com.hust.party.service;

import com.hust.party.pojo.KuaiDiUser;
import com.hust.party.vo.UserInfoVO;

/**
 * <br/>
 * fan 2018/6/15 7:33
 */
public interface KuaidiUserService extends BaseService<KuaiDiUser>{
    /**
     * 根据微信给的id来查找用户
     */
    Integer selectUserByOpenId(String open_id);

    /**
     * 返回用用户信息页的数据
     * @param uid
     * @return
     */
    KuaiDiUser selectUserInfo(Integer uid);
}