package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.dao.KuaiDiProvinceMapper;
import com.hust.party.pojo.KuaiDiProvince;
import com.hust.party.pojo.KuaiDiSchool;
import com.hust.party.pojo.KuaiDiUserContext;
import com.hust.party.service.KuaidiProvinceService;
import com.hust.party.service.KuaidiSchoolService;
import com.hust.party.service.KuaidiUserContextService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class KuaiDiSchoolController
{

    @Autowired
    private KuaidiProvinceService kuaidiProvinceService;

  @Autowired
  private KuaidiSchoolService kuaidiSchoolService;

    @RequestMapping(value = "/getAllProvince", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有省份信息", httpMethod = "GET")
    @ResponseBody
    public ReturnMessage getAllProvinces() {
        List<KuaiDiProvince> kuaiDiProvinces = kuaidiProvinceService.selectAllProvince();
        return new ReturnMessage(200, kuaiDiProvinces);
    }

    @RequestMapping(value = "/getSchoolByProvinceId", method = RequestMethod.GET)
    @ApiOperation(value = "根据指定省份获取其下所有的学校", httpMethod = "GET")
    @ResponseBody
    public ReturnMessage getSchoolByProvinceId(Integer provinceId) {
        List<KuaiDiSchool> schools = kuaidiSchoolService.getSchoolByProvinceId(provinceId);
        return new ReturnMessage(200,schools);
    }


    @RequestMapping(value = "/school", method = RequestMethod.POST)
    @ApiOperation(value = "插入学校")
    @ResponseBody
    public ReturnMessage insertKuaidiUserContext(KuaiDiSchool kuaiDiSchool) {

         int insert=kuaidiSchoolService.insert(kuaiDiSchool);



        return new ReturnMessage(200, insert);
    }

    }

