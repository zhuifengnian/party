package com.hust.party.dao;

import com.hust.party.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper  extends BaseMapper<User>{

    Integer selectUserByChatId(String openId);
}