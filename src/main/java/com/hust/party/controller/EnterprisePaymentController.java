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
    @RequestMapping(value = "/enterprisePayment/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取账户余额", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getAccount(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid){
        EnterprisePayment enterprisePayment =new EnterprisePayment();
        enterprisePayment.setEnterpriseId(eid);
         List<EnterprisePayment> list=   enterprisePaymentService.select(enterprisePayment,null);
        return new ReturnMessage(200, list.get(0).getAccountMoney());
    }
    @RequestMapping(value = "/enterprisePayment/total/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取累计收入", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage gettotalMoney(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid){
        EnterprisePayment enterprisePayment =new EnterprisePayment();
        enterprisePayment.setEnterpriseId(eid);
        List<EnterprisePayment> list=   enterprisePaymentService.select(enterprisePayment,null);
        return new ReturnMessage(200, list.get(0).getTotalMoney());
    }
    @RequestMapping(value = "/enterprisePayment/List/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取账单列表", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage gettotalList(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=10;
        PageInfo<AccountVO> pageinfo=new PageInfo<AccountVO>();
        List<AccountVO> list2=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        Payment payment =new Payment();
        payment.setEnterpriseId(eid);
          List<Payment>list= paymentService.select(payment,page);
          pageinfo.setTotal(paymentService.selectCount(payment));
           for(int i=0;i<list.size();i++){
               Payment payment1=list.get(i);
               AccountVO accountVO =new AccountVO();
               ReflectUtil.copyProperties(accountVO, payment1);
                  accountVO.setTitle(activityService.selectByPrimaryKey(payment1.getActivityId()).getTitle());
                  list2.add(accountVO);
           }
           pageinfo.setRows(list2);
        return new ReturnMessage(200,pageinfo);
    }
    @RequestMapping(value = "/enterprisePayment/getMoney/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日收入", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNowMoney(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid){
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
        Timestamp t = new Timestamp(zero);
        Date d = new Date(t.getTime());
        Timestamp t1 = new Timestamp(twelve);
        Date d1 = new Date(t1.getTime());
        Payment payment=new Payment();
        payment.setEnterpriseId(eid);
       List<Payment> list= paymentService.select(payment,null);
        BigDecimal aDouble =new BigDecimal(0.00);
       for(int i=0;i<list.size();i++){
            Date date=list.get(i).getCreatTime();
            if(date.getTime()<d1.getTime()&&date.getTime()>d.getTime())
             aDouble=aDouble.add(list.get(i).getPrice());
       }
        return new ReturnMessage(200,aDouble);
    }
}
