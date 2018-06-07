package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.pojo.Orders;
import com.hust.party.pojo.Payment;
import com.hust.party.service.ActivityService;
import com.hust.party.service.EnterprisePaymentService;
import com.hust.party.service.OrdersService;
import com.hust.party.service.PaymentService;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.AccountVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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


}
