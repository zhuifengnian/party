package com.hust.party.service;

import com.hust.party.pojo.User;

/**
 * <br/>
 * fan 2018/5/14 14:00
 */
public interface UserService extends BaseService<User>{
    /**
     * 根据微信给的id来查找用户
     */
    Integer selectUserByChatId(String chat_id);
}