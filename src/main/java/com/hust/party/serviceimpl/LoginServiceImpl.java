package com.hust.party.serviceimpl;

import com.hust.party.dao.PermissionMapper;
import com.hust.party.dao.RoleMapper;
import com.hust.party.dao.UserMapper;
import com.hust.party.pojo.User;
import com.hust.party.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic_Yang on 17/7/6.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserMapper usrdao;

    @Autowired
    private PermissionMapper permdao;

    @Autowired
    private RoleMapper roledao;

    @Override
    public Map queryuser(User user) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        User usr = usrdao.selectUserbyId(user.getId());
        if(usr == null)
            map.put("flag", "null");
        else if(! user.getPassword().equals(usr.getPassword()))
        {
            map.put("flag", "invalid");
        }
        else
            map.put("flag","valid");
        map.put("user", usr);
        return map;

    }

    @Override
    public List<Map<String, Object>> queryPerm(String userName) {
        return permdao.queryPerm(userName);
    }

    @Override
    public List<Map<String, Object>> queryRole(String userName) {
        return roledao.queryRole(userName);
    }


}
