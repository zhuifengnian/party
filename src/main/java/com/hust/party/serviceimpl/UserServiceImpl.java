package com.hust.party.serviceimpl;

import com.hust.party.dao.UserMapper;
import com.hust.party.pojo.User;
import com.hust.party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by Magic_Yang on 17/7/17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper udao;

    @Override
    public User getuserinfo(String id) {

        return udao.selectUserbyId(id);

    }

    @Override
    public void resetpassword(String id) {
        User usr = new User();
        usr.setId(id);
        udao.resetpassword(usr);

    }

    @Override
    public void changepassword(String id,String password) {
        User usr = new User();
        usr.setId(id);
        usr.setPassword(password);
        udao.changepassword(usr);

    }

    @Override
    public void updatecreorbounds(User user) {


        udao.updatecreorbounds(user);
    }

    @Override
    public HashMap<String, Object> getuserdetail(String id) {
        HashMap<String,Object> userlist = new HashMap<String,Object>();
        User userinfo = udao.selectUserbyId(id);
        userlist.put("user",userinfo);
        return userlist;
    }
}
