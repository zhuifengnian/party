package com.hust.party.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @RequestMapping("/test")
    @ApiOperation(value = "教程", httpMethod = "POST", notes = "教程")
    public String logintest(@ApiParam(required = true, name = "name", value = "教程入参") @RequestParam(required = false)
                                    String name,@ApiParam(required = true, name = "password", value = "教程入参")  @RequestParam(required = false) String password){
        return "test";
    }
}
