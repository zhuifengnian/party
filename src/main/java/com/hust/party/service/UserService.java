package com.hust.party.service;

import com.hust.party.pojo.User;
import com.hust.party.vo.UserInfoVO;

/**
 * <br/>
 * fan 2018/5/14 14:00
 */
public interface UserService extends BaseService<User>{
    /**
     * 根据微信给的id来查找用户
     */
    Integer selectUserByChatId(String open_id);

    /**
     * 返回用用户信息页的数据
     * @param uid
     * @return
     */
    UserInfoVO selectUserInfo(Integer uid);
}