package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.PageUtil;
import com.hust.party.util.QiNiuUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.util.TimeUtil;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterpriseInfoVO;
import com.hust.party.vo.PerenceActivityVO;
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

    @ApiOperation(value = "查看活动", notes = "查看活动")
    @ResponseBody
    @RequestMapping(value = "/enterprise/checkActivity", method = RequestMethod.POST)
    public ReturnMessage checkActivity(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {

        PageInfo<PerenceActivityVO> pageInfo = activityService.getEnterpriseActivity(name, id, PageUtil.setPage(pageNumber));
        return new ReturnMessage(200, pageInfo);
    }


    @ApiOperation(value = "查看订单", notes = "查看订单")
    @ResponseBody
    @RequestMapping(value = "/enterprise/checkOrder", method = RequestMethod.POST)
    public ReturnMessage checkOrder(@RequestParam("eid") Integer eid, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        Timestamp timestamp[] = TimeUtil.getTime();
        PageInfo<AllOrderVO> pageInfo = enterpriseService.getEnterpriseOrder(name,eid,timestamp[0],timestamp[1],PageUtil.setPage(pageNumber));
        return new ReturnMessage(200, pageInfo);
    }

    @RequestMapping(value = "/enterprise/insertEnterprise", method = RequestMethod.POST)
    @ApiOperation(value = "企业注册接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterprise(Enterprise enterprise) {
        Integer id = enterpriseService.selectUserByChatId(enterprise.getOpenId());
        Integer insert = null;
        if (null == id) {
            enterprise.setState(1);
            insert = enterpriseService.insert(enterprise);
            if (insert != null) {
                EnterprisePayment enterprisePayment = new EnterprisePayment();
                enterprisePayment.setEnterpriseId(insert);

                 enterprisePaymentService.insert(enterprisePayment);
            }
        }


        return new ReturnMessage(200, insert);
    }

    @RequestMapping(value = "/enterprise/insertEnterpriseLicence", method = RequestMethod.POST)
    @ApiOperation(value = "企业上传营业执照接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterpriseLicence(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam(value = "flyfile", required = false) MultipartFile flfile) {
        String picture = "";
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseId);
        if (null != flfile) {
            picture = QiNiuUtil.manageFile(flfile);
        }
        enterprise.setLicence(picture);
        int insert = enterpriseService.updateByPrimaryKeySelective(enterprise);
        return new ReturnMessage(200, insert);
    }

    @ApiOperation(value = "添加活动", notes = "添加活动")
    @ResponseBody
    @RequestMapping(value = "/enterprise/insertActivity", method = RequestMethod.POST)
    public ReturnMessage insertActivity(Activity activity, @RequestParam("eid") Integer eid) {
        activity.setEnterpriseId(eid);
        activity.setState(1);
        Integer insert = activityService.insert(activity);
        return new ReturnMessage(200, insert);
    }

    @RequestMapping(value = "/enterprise/insertActivityPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储图片信息")
    @ResponseBody
    public ReturnMessage insertActivityPicture(@RequestParam("activityId") Integer activityId, @RequestParam(value = "flyfile", required = false) MultipartFile flfile, Integer num) {
        String picture = "";
        Activity activity = new Activity();
        activity.setId(activityId);
        if (flfile != null)
            picture = QiNiuUtil.manageFile(flfile);
        if (num == 1) {
            activity.setPicture(picture);
        } else if (num == 2) {
            activity.setVideo(picture);
        }

        int insert = activityService.updateByPrimaryKeySelective(activity);

        return new ReturnMessage(200, insert);
    }

    @RequestMapping(value = "/enterprise/updateEnterprise", method = RequestMethod.POST)
    @ApiOperation(value = "修改企业信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateEnterprise(Enterprise enterprise){
      int insert=0;
       if(enterprise.getId()!=null) {
           insert = enterpriseService.updateByPrimaryKeySelective(enterprise);
       }
        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/enterprise/deleteActivity", method = RequestMethod.POST)
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
    @RequestMapping(value = "/enterprise/scanOrders", method = RequestMethod.POST)
    @ApiOperation(value = "企业扫描二维码", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage scanOders(@RequestParam("qr_code") String qr_code){
         Orders order=new Orders();
         order.setQrCode(qr_code);
         int []insert = new int[4];
        List<Orders> orders =ordersService.select(order,null);
         //修改orders下的状态
        if(orders.size()!=0){
            order.setId(orders.get(0).getId());
            order.setState(5);
            insert[0]=ordersService.updateByPrimaryKeySelective(order);

          List<Integer> user= orderUserService.getUserId(orders.get(0).getId());
            insert[1]= userForceService.insertForce(user);
            //获取payment中的订单钱数
            Payment payment =new Payment();

            payment.setOrderId(orders.get(0).getId());
         List<Payment> list=  paymentService.select(payment,null);
         if(list.size()!=0) {
             payment.setId(list.get(0).getId());
             payment.setState(1);
             insert[2] = paymentService.updateByPrimaryKeySelective(payment);
             //修改enterprisepayment中的钱数

             EnterprisePayment enterprisePayment = new EnterprisePayment();
             enterprisePayment.setEnterpriseId(list.get(0).getEnterpriseId());
             List<EnterprisePayment> enterprisePayments = enterprisePaymentService.select(enterprisePayment, null);
             if(enterprisePayments.size()!=0) {
                 enterprisePayment.setId(enterprisePayments.get(0).getId());
                 enterprisePayment.setAccountMoney(enterprisePayments.get(0).getAccountMoney().add(list.get(0).getPrice()));
                 enterprisePayment.setTotalMoney(enterprisePayments.get(0).getTotalMoney().add(list.get(0).getPrice()));
                 insert [3]= enterprisePaymentService.updateByPrimaryKeySelective(enterprisePayment);
             }
         }

        }

        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/enterprise/updateActivity", method = RequestMethod.POST)
    @ApiOperation(value = "企业修改自己的活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateActivity(Activity activity){

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
    @RequestMapping(value = "/enterprise/getEnterpriseInfo", method = RequestMethod.POST)
    public ReturnMessage getEnterpriseInfo(@RequestParam("eid") Integer eid){
        EnterpriseInfoVO enterpriseInfoVO =enterpriseService.selectEnterpriseInfo(eid);
        if(enterpriseInfoVO== null){
            throw new ApiException(201, "所传uid没有数据");
        }
        return new ReturnMessage(200, enterpriseInfoVO);
    }
}
