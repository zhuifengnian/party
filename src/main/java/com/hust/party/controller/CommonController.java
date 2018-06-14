package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.service.KuaidiSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于一些常用的公共接口配置<br/>
 * fan 2018/5/14 1:05
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    KuaidiSmsService kuaidiSmsService;

    @RequestMapping("/404")
    @ResponseBody
    public ReturnMessage pageNotFoundException(){
        return new ReturnMessage(404, "请求的接口地址未找到，请检查");
    }
    @RequestMapping("/400")
    @ResponseBody
    public ReturnMessage paramException(Exception e){
        System.out.println(e.getMessage());
        return new ReturnMessage(400, "请求的参数有问题，请检查" + e.getMessage());
    }
    @RequestMapping("/500")
    @ResponseBody
    public ReturnMessage ServerException(){
        return new ReturnMessage(500, "服务器状态异常");
    }

    @RequestMapping(value = "/testNL", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage testNL(String msg){
        String s = kuaidiSmsService.extractExpressCode(msg);
        return new ReturnMessage(200, s);
    }

}