package com.hust.party.service;


import com.hust.party.pojo.UserForce;

import java.util.Date;
import java.util.List;

public interface UserForceService extends BaseService<UserForce>{
     int insertForce(int []num);
     int insertcolonelForce(Integer user_id);
     int insertCommonUserForce(Integer user_id);

}
