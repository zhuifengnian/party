package com.hust.party.controller;


import com.google.gson.Gson;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.User;
import com.hust.party.service.UserService;
import com.hust.party.wxpay.ConstantUtil;
import com.hust.party.wxpay.TenpayHttpClient;
import com.hust.party.wxpay.XMLUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


/**
 * Created by luyue on 2018/5/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final String GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";
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

    @ApiOperation(value = "返回当前用户的openid", notes = "获取用户的openid，这里是通过服务器向微信的服务器发送请求获得的，需要传入js_code")
    @ResponseBody
    @RequestMapping(value="/getopenid", method = RequestMethod.POST)
    public ReturnMessage getopenid(@RequestParam("js_code")String js_code){

        TenpayHttpClient httpClient = new TenpayHttpClient();
        String tmpUrl = GET_OPENID_URL + "?appid="+ConstantUtil.APP_ID +"&secret="+ConstantUtil.APP_SECRET+"&js_code="+js_code+"&grant_type=authorization_code";
        try {
            httpClient.httpGetMethod(tmpUrl);
            String resContent = httpClient.getResContent();
            return new ReturnMessage(200, resContent);
        } catch (IOException e) {
            throw new ApiException(201, "网络请求出现错误");
        }
    }

}
