package com.hust.party.service;


import com.hust.party.pojo.UserForce;

import java.util.Date;
import java.util.List;

public interface UserForceService extends BaseService<UserForce>{
    public boolean insertForce(int []num);
}
