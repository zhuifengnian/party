package com.hust.party.service;


import com.hust.party.pojo.UserForce;

import java.util.Date;
import java.util.List;

public interface UserForceService extends BaseService<UserForce>{
     boolean insertForce(int []num);
     boolean insertcolonelForce(Integer user_id);
     boolean insertCommonUserForce(Integer user_id);

}
