package com.hust.party.dao;

import com.hust.party.pojo.User;
import com.hust.party.vo.UserInfoVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper  extends BaseMapper<User>{

    Integer selectUserByChatId(String openId);

    UserInfoVO selectUserInfo(Integer uid);
}