package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.UserForceMapper;
import com.hust.party.pojo.UserForce;
import com.hust.party.service.UserForceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/6/5.
 */
@Service
public class UserForceServiceImpl extends  AbstractBaseServiceImpl<UserForce> implements UserForceService {

   @Autowired
   private UserForceMapper userForceMapper;
   @Autowired
   private UserForceService userForceService;
    @Override
    public BaseMapper<UserForce> getDao() {
        return userForceMapper;
    }

    @Override
    public boolean insertForce(int[] num){
            boolean insertNum=false;
            UserForce userForce =new UserForce();

            for(int i=0;i<num.length;i++){
                userForce.setUserId(num[i]);
                List<UserForce> userForces= userForceService.select(userForce,null);

                if(i==0)
                    userForce.setUserForce(userForces.get(0).getUserForce()+10);
                else
                    userForce.setUserForce(userForces.get(0).getUserForce()+2);
                userForce.setUpdateTime(new Date());
                Integer insert=userForceMapper.updateByPrimaryKeySelective(userForce);
                if(insert!=null)
                    insertNum=true;
            }

            return insertNum;
        }

    @Override
    public boolean insertcolonelForce(Integer user_id) {
        boolean insertNum=false;
        UserForce userForce =new UserForce();
        userForce.setUserId(user_id);
        List<UserForce> userForces= userForceService.select(userForce,null);
        userForce.setUserForce(userForces.get(0).getUserForce()+10);
        Integer insert=userForceMapper.updateByPrimaryKeySelective(userForce);
        if(insert==userForces.get(0).getUserId())
            insertNum=true;
        return insertNum;
    }

    @Override
    public boolean insertCommonUserForce(Integer user_id) {

        boolean insertNum=false;
        UserForce userForce =new UserForce();
        userForce.setUserId(user_id);
        List<UserForce> userForces= userForceService.select(userForce,null);
        userForce.setUserForce(userForces.get(0).getUserForce()+2);
        Integer insert=userForceMapper.updateByPrimaryKeySelective(userForce);
        if(insert==userForces.get(0).getUserId())
            insertNum=true;
        return insertNum;
    }
}

