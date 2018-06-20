package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.KuaiDiSchool;
import com.hust.party.pojo.KuaiDiUserContext;
import com.hust.party.service.KuaidiSchoolService;
import com.hust.party.service.KuaidiUserContextService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class KuaiDiSchoolController
{
  @Autowired
  private KuaidiSchoolService kuaidiSchoolService;


    @RequestMapping(value = "/school", method = RequestMethod.POST)
    @ApiOperation(value = "插入学校")
    @ResponseBody
    public ReturnMessage insertKuaidiUserContext(KuaiDiSchool kuaiDiSchool) {

         int insert=kuaidiSchoolService.insert(kuaiDiSchool);



        return new ReturnMessage(200, insert);
    }



    }

