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
@RequestMapping("/user")
public class UserController
{
@Autowired
private UserService userService;
    @ApiOperation(value = "插入用户", notes = "插入用户到数据库，当用户已经存在时，不插入，会返回这个用户在数据库中的uid")
    @ResponseBody
    @RequestMapping(value="/insertAndLogin", method = RequestMethod.POST)
    public ReturnMessage insertAndLogin( @RequestBody User user){
        //当用户不存在时，在数据库中先记录这个用户
        //在数据库中根据open_id查找用户是否存在
        Integer uid = userService.selectUserByChatId(user.getOpenId());
        if(uid == null){
            //执行插入操作
            uid = userService.insert(user);
        }
        return new ReturnMessage(200, uid);

    }

}
