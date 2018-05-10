package com.hust.party.service;

import com.hust.party.pojo.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Magic_Yang on 17/7/17.
 */
public interface UserService {
     User getuserinfo(String id);

     void resetpassword(String id);

     void changepassword(String id, String password);

     void updatecreorbounds(User user);

     HashMap<String,Object> getuserdetail(String id);

}
