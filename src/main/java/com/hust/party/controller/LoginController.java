package com.hust.party.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
