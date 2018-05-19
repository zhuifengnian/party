package com.hust.party.controller;


import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.User;
import com.hust.party.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class UserController
{
@Autowired
private UserService userService;
    @ApiOperation(value = "插入用户", notes = "插入用户到数据库")
    @ResponseBody
    @RequestMapping(value="/insertUser", method = RequestMethod.POST)
    public ReturnMessage insertActivity( @RequestBody User user){

        int insertNum = userService.insert(user);
        return new ReturnMessage(200, insertNum);

    }



}
