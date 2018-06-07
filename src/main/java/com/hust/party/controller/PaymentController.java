package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.EnterprisePayment;
import com.hust.party.pojo.Payment;
import com.hust.party.service.ActivityService;
import com.hust.party.service.EnterprisePaymentService;
import com.hust.party.service.OrdersService;
import com.hust.party.service.PaymentService;
import com.hust.party.vo.AccountVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by luyue on 2018/5/23
 */
@Controller
public class PaymentController
{

@Autowired
private PaymentService paymentService;


    @RequestMapping(value = "/Payment/List", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取账单列表", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage gettotalList(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AccountVO> pageinfo=new PageInfo<AccountVO>();

        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<AccountVO> list2=paymentService.gettotalList(eid,page);
           pageinfo.setRows(list2);
           pageinfo.setTotal(paymentService.gettotalListCount(eid));
        return new ReturnMessage(200,pageinfo);
    }
    @RequestMapping(value = "/Payment/getNowMoney", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日收入", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNowMoney(@RequestParam("eid") Integer eid){
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
            Date date=list.get(i).getCreateTime();
            if(date.getTime()<d1.getTime()&&date.getTime()>d.getTime())
                aDouble=aDouble.add(list.get(i).getPrice());
        }
        return new ReturnMessage(200,aDouble);
    }
}
