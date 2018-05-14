package com.hust.party.controller;

import com.hust.party.exception.ApiExpection;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private ActivityService activityService;
    @RequestMapping("/test")
    @ApiOperation(value = "教程", httpMethod = "POST", notes = "教程")
    public String logintest(@ApiParam(required = true, name = "name", value = "教程入参") @RequestParam(required = false)
                                    String name,@ApiParam(required = true, name = "password", value = "教程入参")  @RequestParam(required = false) String password){
        Activity activity = activityService.selectByPrimaryKey(1);
        throw new ApiExpection(501, "测试。。。");
    }
}
