package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.common.SolrUtil;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Category;
import com.hust.party.pojo.Enterprise;
import com.hust.party.pojo.UserForce;
import com.hust.party.service.*;
import com.hust.party.util.QiNiuUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.ActivityVo;
import com.hust.party.vo.PerenceActivityVO;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/6/5
 */
@Controller
public class UserForceController
{
    @Autowired
    private UserForceService userForceService;
    @ApiOperation(value = "插入金币", notes = "插入金币到数据库")
    @ResponseBody
    @RequestMapping(value="/insertUserForce", method = RequestMethod.POST)
    public ReturnMessage insertUserForce( @RequestParam("user_id") Integer user_id ){
        int insertNum=0;
        UserForce userForce =new UserForce();
        userForce.setUserId(user_id);
       List<UserForce> userForces= userForceService.select(userForce,null);

           Integer gold=userForces.get(0).getGold()+10;
           userForce.setGold(gold);

            userForceService.updateByPrimaryKey(userForce);
           Date date1 =new Date();
           Date date2 = userForces.get(0).getUpdateTime();
           long  between = date1.getTime() - date2.getTime();
           if(between <(24* 3600000)){
            if(userForces.get(0).getTimeState()!=5) {
                userForce.setTimeState(userForces.get(0).getTimeState() + 1);
                userForce.setUserMedal(userForces.get(0).getUserMedal()+1);
            }
            else
            {
                userForce.setGold(gold+30);

                userForce.setTimeState(0);
            }
           }
        if( userForce.getUserForce()>50&&userForce.getUserForce()<=200)
            userForce.setUserMedal("铜牌团长");
        else if( userForce.getUserForce()>200&&userForce.getUserForce()<=500)
            userForce.setUserMedal("银牌团长");
        else if( userForce.getUserForce()>500)
            userForce.setUserMedal("金牌团长");
           userForce.setId(userForces.get(0).getId());
           userForce.setUpdateTime(new Date());
          insertNum= userForceService.updateByPrimaryKeySelective(userForce);

        return new ReturnMessage(200, insertNum);

    }


}
