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
    public int insertForce(List<Integer> userId){
            int insertNum=0;
            UserForce userForce =new UserForce();

            for(int i=0;i<userId.size();i++) {
                userForce.setUserId(userId.get(i));
                List<UserForce> userForces = userForceService.select(userForce, null);
                userForce.setId(userForces.get(0).getId());
                if (userForces.size() != 0) {
                    if (i == 0)
                        userForce.setUserForce(userForces.get(0).getUserForce() + 10);
                    else
                        userForce.setUserForce(userForces.get(0).getUserForce() + 2);

                    if( userForce.getUserForce()>50&&userForce.getUserForce()<=200)
                        userForce.setUserMedal("铜牌团长");
                    else if( userForce.getUserForce()>200&&userForce.getUserForce()<=500)
                        userForce.setUserMedal("银牌团长");
                    else if( userForce.getUserForce()>500)
                        userForce.setUserMedal("金牌团长");
                    Integer insert = userForceMapper.updateByPrimaryKeySelective(userForce);
                    if (insert != null)
                        insertNum = 1;
                }
                else {
                    insertNum = 2;
                   break;
                }
            }

            return insertNum;
        }

    @Override
    public int insertcolonelForce(Integer user_id) {
        int insertNum=0;
        UserForce userForce =new UserForce();
        userForce.setUserId(user_id);
        List<UserForce> userForces= userForceService.select(userForce,null);
        if(userForces.size()!=0) {
            userForce.setId(userForces.get(0).getId());
            userForce.setUserForce(userForces.get(0).getUserForce() + 10);

            if( userForce.getUserForce()>50&&userForce.getUserForce()<=200)
                userForce.setUserMedal("铜牌团长");
            else if( userForce.getUserForce()>200&&userForce.getUserForce()<=500)
                userForce.setUserMedal("银牌团长");
            else if( userForce.getUserForce()>500)
                userForce.setUserMedal("金牌团长");

            Integer insert = userForceMapper.updateByPrimaryKeySelective(userForce);
            if (insert != null)
                insertNum = 1;
        }
        else
            insertNum=2;

        return insertNum;
    }

    @Override
    public int insertCommonUserForce(Integer user_id) {

        int insertNum=0;
        UserForce userForce =new UserForce();
        userForce.setUserId(user_id);
        List<UserForce> userForces= userForceService.select(userForce,null);
        if(userForces.size()!=0) {
            userForce.setId(userForces.get(0).getId());
            userForce.setUserForce(userForces.get(0).getUserForce() + 2);
            if( userForce.getUserForce()>50&&userForce.getUserForce()<=200)
                userForce.setUserMedal("铜牌团长");
            else if( userForce.getUserForce()>200&&userForce.getUserForce()<=500)
                userForce.setUserMedal("银牌团长");
            else if( userForce.getUserForce()>500)
                userForce.setUserMedal("金牌团长");
            Integer insert = userForceMapper.updateByPrimaryKeySelective(userForce);
            if (insert != null)
                insertNum = 1;
        }
        else
            insertNum=2;
        return insertNum;
    }
}

