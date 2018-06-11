package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.service.SmsService;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * 短信通知的controller<br/>
 * fan 2018/6/12 0:48
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;
    @Value("${sms.verify.templateid}")
    private String verifyTemplayteid;

    @PostMapping("/verifyPhone")
    @ApiOperation(value = "为手机发送验证码", notes = "为手机发送验证码")
    public ReturnMessage verifyPhone(@RequestParam String phone){
        Random random = new Random();
        int randomVal = 100000 + random.nextInt(900000);    //随机生成6位数的验证码
        String ret = smsService.sendSms(verifyTemplayteid, "" + randomVal, phone, "");
        return new ReturnMessage(200, ret);
    }

    @PostMapping("/verifyMulPhone")
    @ApiOperation(value = "短信群发接口", notes = "短信群发，需要传入多个手机号")
    public ReturnMessage verifyMulPhone(@RequestParam List<String> phones){
        Random random = new Random();
        int randomVal = 100000 + random.nextInt(900000);    //随机生成6位数的验证码
        String afterPhones = StringUtils.join(phones, ",");
        String ret = smsService.sendSmsBatch(verifyTemplayteid, "" + randomVal, afterPhones, "");
        return new ReturnMessage(200, ret);
    }
}