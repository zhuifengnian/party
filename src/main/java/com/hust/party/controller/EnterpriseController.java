package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.QiNiuUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterpriseInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by luyue on 2018/5/23
 */
@Controller
public class EnterpriseController
{
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderUserService orderUserService;
    @Autowired
    private UserForceService userForceService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EnterprisePaymentService enterprisePaymentService;
    @RequestMapping(value = "/enterprise/activity", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getEnterpriseActivity(@RequestParam("eid")  Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;

        PageInfo<EnterpriseActivityVo> pageinfo=new PageInfo<EnterpriseActivityVo>();
        List<EnterpriseActivityVo> list1= new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
      List<EnterpriseActivityVo>list=  enterpriseService.getAllActivity(eid,page);

        pageinfo.setRows(list);
        pageinfo.setTotal(enterpriseService.getAllActivityCount(eid));

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getPayOrders", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getPayOrders(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
       if(pageSize==null)
           pageSize=4;
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        Timestamp t = new Timestamp(zero);
        Timestamp t1 = new Timestamp(twelve);

           List<AllOrderVO> list1=enterpriseService.getPayOrders(eid,t,t1,page);
            pageinfo.setTotal(enterpriseService.getPayCount(eid,t,t1));


        pageinfo.setRows(list1);

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getAllActivity", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取全部订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getAllActivity(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();

        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<AllOrderVO> list2=enterpriseService.getAllOrder(eid,page);


        pageinfo.setRows(list2);
        pageinfo.setTotal(enterpriseService.getAllOrderCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNewOrder", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日新接订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNewOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;

        Timestamp t = new Timestamp(zero);
        Timestamp t1 = new Timestamp(twelve);

        List<AllOrderVO> list1=enterpriseService.getNewOrders(eid,t,t1,page);
        pageinfo.setTotal(enterpriseService.getNewCount(eid,t,t1));

         pageinfo.setRows(list1);

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNoOrder", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取未消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNoOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();

        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<AllOrderVO> list3=enterpriseService.getNoOrder(eid,page);
        pageinfo.setRows(list3);
        pageinfo.setTotal(enterpriseService.getNoOrderCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getYOrder", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getYOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<AllOrderVO> list3=enterpriseService.getYOrder(eid,page);
        pageinfo.setRows(list3);
        pageinfo.setTotal(enterpriseService.getYOrderCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getQOrder", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已取消订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getQOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<AllOrderVO> list3=enterpriseService.getQOrder(eid,page);
        pageinfo.setRows(list3);
        pageinfo.setTotal(enterpriseService.getQOderCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getEntepriseQoders", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取企业自己取消订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getEnterpriseQoders(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<AllOrderVO> list3=enterpriseService.getEnterpriseQOrders(eid,page);
        pageinfo.setRows(list3);
        pageinfo.setTotal(enterpriseService.getEnterpriseQOrdersCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise", method = RequestMethod.POST)
    @ApiOperation(value = "企业注册接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterprise(Enterprise enterprise,@RequestParam(value = "flyfile", required = false) MultipartFile flfile){
        Integer eid = enterpriseService.selectUserByChatId(enterprise.getOpenId());
        int insert=0;
        if(eid==null) {
            String license = null;
            if (flfile != null)
                license = QiNiuUtil.manageFile(flfile);
            enterprise.setLicence(license);
           insert = enterpriseService.insert(enterprise);
        }
        else
            insert=0;
        return new ReturnMessage(200, insert);
    }

    @RequestMapping(value = "/updateEnterprise", method = RequestMethod.POST)
    @ApiOperation(value = "修改企业信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateEnterprise(Enterprise enterprise,@RequestParam(value = "flyfile", required = false) MultipartFile flfile){
      int insert=0;
       if(enterprise.getId()!=null) {
           String license = null;
           if (flfile != null)
               license = QiNiuUtil.manageFile(flfile);
           ;
           enterprise.setLicence(license);
           insert = enterpriseService.updateByPrimaryKeySelective(enterprise);
       }
        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ApiOperation(value = "企业撤销自己的活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage deleteActivity(@RequestParam("aid") Integer aid){
      String text="";
      Activity activity =new Activity() ;
      activity.setState(2);
      activity.setId(aid);

      Activity activity1=activityService.selectByPrimaryKey(aid);
      if(activity1.getArriveCopies()!=0)
         text="活动卖出，暂时无法取消,可以进行修改";
      else {

              activity.setState(2);
          int insert = activityService.updateByPrimaryKey(activity);
          if (insert != aid)
               text="撤销未成功，请重新撤销";
          else
               text="撤销完成";
      }
        return new ReturnMessage(200, text);
    }
    @RequestMapping(value = "/scanOrders", method = RequestMethod.POST)
    @ApiOperation(value = "企业扫描二维码", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage scanOders(@RequestParam("qr_code") String qr_code){
         Orders order=new Orders();
         order.setQrCode(qr_code);
         int insert=0;
        List<Orders> orders =ordersService.select(order,null);
         //修改orders下的状态
        if(orders.size()!=0){
            order.setId(orders.get(0).getId());
            order.setState(5);
            insert=ordersService.updateByPrimaryKeySelective(order);

          List<Integer> user= orderUserService.getUserId(orders.get(0).getId());
            insert= userForceService.insertForce(user);
            //获取payment中的订单钱数
            Payment payment =new Payment();

            payment.setOrderId(orders.get(0).getId());
         List<Payment> list=  paymentService.select(payment,null);
         if(list.size()!=0) {
             payment.setId(list.get(0).getId());
             payment.setState(1);
             insert = paymentService.updateByPrimaryKeySelective(payment);
             //修改enterprisepayment中的钱数

             EnterprisePayment enterprisePayment = new EnterprisePayment();
             enterprisePayment.setEnterpriseId(list.get(0).getEnterpriseId());
             List<EnterprisePayment> enterprisePayments = enterprisePaymentService.select(enterprisePayment, null);
             if(enterprisePayments.size()!=0) {
                 enterprisePayment.setId(enterprisePayments.get(0).getId());
                 enterprisePayment.setAccountMoney(enterprisePayments.get(0).getAccountMoney().add(list.get(0).getPrice()));
                 enterprisePayment.setTotalMoney(enterprisePayments.get(0).getTotalMoney().add(list.get(0).getPrice()));
                 insert = enterprisePaymentService.updateByPrimaryKeySelective(enterprisePayment);
             }
         }

        }

        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    @ApiOperation(value = "企业修改自己的活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateActivity(  Activity activity){

        String text="";

            Activity activity1 = activityService.selectByPrimaryKey(activity.getId());
            if (activity1.getState() == 1) {
                if (activity.getCopies() != null) {
                    if (activity.getCopies() < activity1.getArriveCopies())
                        text = "暂时不能修改，总份数小于已拼团份数";
                    else {
                        int insert = activityService.updateByPrimaryKey(activity);
                        if (insert != activity.getId())
                            text = "修改未成功，请重新修改";
                        else
                            text = "修改完成";
                    }
                }
                int insert = activityService.updateByPrimaryKey(activity);
                if (insert != activity.getId())
                    text = "修改未成功，请重新修改";
                else
                    text = "修改完成";
            } else
                text = "已撤销活动，不可以修改";


        return new ReturnMessage(200, text);
    }

    @ApiOperation(value = "返回企业详细信息", notes = "返回企业详细信息")
    @ResponseBody
    @RequestMapping(value = "/getEnterpriseInfo", method = RequestMethod.POST)
    public ReturnMessage getEnterpriseInfo(@RequestParam("eid") Integer eid){
        EnterpriseInfoVO enterpriseInfoVO =enterpriseService.selectEnterpriseInfo(eid);
        if(enterpriseInfoVO== null){
            throw new ApiException(201, "所传uid没有数据");
        }
        return new ReturnMessage(200, enterpriseInfoVO);
    }
}
