package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.service.*;
import com.hust.party.vo.EnterprisePaymentInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by luyue on 2018/5/23
 */
@Controller
public class EnterprisePaymentController
{
@Autowired
private EnterprisePaymentService enterprisePaymentService;
@Autowired
private OrdersService ordersService;
@Autowired
private PaymentService paymentService;
@Autowired
private ActivityService activityService;
@Autowired
private EnterpriseService enterpriseService;
    @RequestMapping(value = "/enterprisePayment", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取账户余额", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getAccount(@RequestParam("eid")  Integer eid){
        EnterprisePayment enterprisePayment =new EnterprisePayment();
        enterprisePayment.setEnterpriseId(eid);
         List<EnterprisePayment> list=   enterprisePaymentService.select(enterprisePayment,null);
        return new ReturnMessage(200, list.get(0).getAccountMoney());
    }
    @RequestMapping(value = "/enterprisePayment/total", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取累计收入", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage gettotalMoney(@RequestParam("eid") Integer eid){
        EnterprisePayment enterprisePayment =new EnterprisePayment();
        enterprisePayment.setEnterpriseId(eid);
        List<EnterprisePayment> list=   enterprisePaymentService.select(enterprisePayment,null);
        return new ReturnMessage(200, list.get(0).getTotalMoney());
    }
    @ApiOperation(value = "返回企业账户信息", notes = "返回企业账户信息")
    @ResponseBody
    @RequestMapping(value = "/enterprisePayment/getEnterpriseInfo", method = RequestMethod.POST)
    public ReturnMessage getEnterpriseInfo(@RequestParam("eid") Integer eid){
        EnterprisePaymentInfoVO enterpriseInfoVO =enterpriseService.selectEnterpriseInfo(eid);
        if(enterpriseInfoVO== null){
            throw new ApiException(201, "所传uid没有数据");
        }
        return new ReturnMessage(200, enterpriseInfoVO);
    }

}
