package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Orders;
import com.hust.party.pojo.User;
import com.hust.party.service.ActivityService;
import com.hust.party.service.OrdersService;
import com.hust.party.service.TenPayService;
import com.hust.party.service.UserService;
import com.hust.party.wxpay.*;
import io.swagger.annotations.ApiOperation;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class TenPayController {

//    @Autowired
//    private PayRecordService payRecordService;
//    @Autowired
//    private AppCustomerService appCustomerService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private TenPayService tenpayService;

//    /**
//     * 生成预支付订单，获取prepayId
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Auth
//    @RequestMapping(value = "/app/tenpay/prepay", method = RequestMethod.POST)
//    public @ResponseBody
//    Map<String, Object> getOrder(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        // 获取生成预支付订单的请求类
//        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
//        String totalFee = request.getParameter("total_fee");
//        int total_fee=(int) (Float.valueOf(totalFee)*100);
//        System.out.println("total:"+total_fee);
//        System.out.println("total_fee:" + total_fee);
//        prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
//        prepayReqHandler.setParameter("body", ConstantUtil.BODY);
//        prepayReqHandler.setParameter("mch_id", ConstantUtil.MCH_ID);
//        String nonce_str = WXUtil.getNonceStr();
//        prepayReqHandler.setParameter("nonce_str", nonce_str);
//        prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
//        out_trade_no = String.valueOf(UUID.next());
//        prepayReqHandler.setParameter("out_trade_no", out_trade_no);
//        prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
//        String timestamp = WXUtil.getTimeStamp();
//        prepayReqHandler.setParameter("time_start", timestamp);
//        System.out.println(String.valueOf(total_fee));
//        prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));
//        prepayReqHandler.setParameter("trade_type", "APP");
//        /**
//         * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
//         */
//        prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
//        prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
//        String prepayid = prepayReqHandler.sendPrepay();
//        // 若获取prepayid成功，将相关信息返回客户端
//        if (prepayid != null && !prepayid.equals("")) {
//            PayRecord payRecord=new PayRecord();
//            AppCustomer appCustomer=(AppCustomer) request.getSession().getAttribute("appCustomer");
//            payRecord.setPhone(appCustomer.getPhone());
//            payRecord.setSerialId(Long.valueOf(out_trade_no));
//            payRecord.setType((byte)2);
//            payRecord.setGenerateTime(new Date());
//            payRecord.setTotalAmount(Float.valueOf(total_fee)/100);
//            payRecordService.insert(payRecord);
//            String signs = "appid=" + ConstantUtil.APP_ID + "&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid="
//                    + ConstantUtil.PARTNER_ID + "&prepayid=" + prepayid + "&timestamp=" + timestamp + "&key="
//                    + ConstantUtil.APP_KEY;
//            map.put("code", 0);
//            map.put("info", "success");
//            map.put("prepayid", prepayid);
//            /**
//             * 签名方式与上面类似
//             */
//            map.put("sign", MD5Util.MD5Encode(signs, "utf8").toUpperCase());
//            map.put("appid", ConstantUtil.APP_ID);
//            map.put("timestamp", timestamp);  //等于请求prepayId时的time_start
//            map.put("noncestr", nonce_str);   //与请求prepayId时值一致
//            map.put("package", "Sign=WXPay");  //固定常量
//            map.put("partnerid", ConstantUtil.PARTNER_ID);
//        } else {
//            map.put("code", 1);
//            map.put("info", "获取prepayid失败");
//        }
//        return map;
//    }
    /**
     * 用户发起订单的支付接口，返回prepayId
     */
    @RequestMapping(value = "/app/tenpay/generatorPrepay", method = RequestMethod.POST)
    @ApiOperation(value = "发起订单者获取支付的prepayid", httpMethod = "POST")
    public @ResponseBody
    Map<String, Object> generatorPrepay(@RequestParam("uid") Integer uid, @RequestParam("aid") Integer aid, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //拿到用户数据
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "所给用户id不存在");
        }
        String openid = user.getOpenId();

        //拿到所传订单
        Activity activity = activityService.selectByPrimaryKey(aid);
        if(activity == null){
            throw new ApiException(201, "所给活动id不存在");
        }

        Map<String, Object> map = tenpayService.getPrepayId(openid, aid, true, request, response);
        return map;
    }

    /**
     * 生成预支付订单，获取prepayId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/tenpay/engagerPrepay", method = RequestMethod.POST)
    @ApiOperation(value = "参与订单者获取支付的prepayid", httpMethod = "POST")
    public @ResponseBody
    Map<String, Object> engagerPrepay(@RequestParam("uid") Integer uid, @RequestParam("oid") Integer oid, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //拿到用户数据
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "所给用户id不存在");
        }
        String openid = user.getOpenId();

        // 获取生成预支付订单的请求类
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        //拿到所传订单
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201, "所给订单id不存在");
        }
        Integer aid = orders.getActivityId();

        Map<String, Object> map = tenpayService.getPrepayId(openid, aid, false, request, response);

        return map;
    }

    /**
     * 接收微信支付成功通知
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/app/tenpay/notify", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信支付后台的通知", httpMethod = "get")
    public void getnotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("微信支付回调");
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        System.out.println("微信支付通知结果：" + result);
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("=========:"+result);
//        // 若支付成功，则告知微信服务器收到通知
//        if (map.get("return_code").equals("SUCCESS")) {
//            if (map.get("result_code").equals("SUCCESS")) {
//                System.out.println("充值成功！");
//                PayRecord payRecord=payRecordService.get(Long.valueOf(map.get("out_trade_no")));
//                System.out.println("订单号："+Long.valueOf(map.get("out_trade_no")));
//                System.out.println("payRecord.getPayTime():"+payRecord.getPayTime()==null+","+payRecord.getPayTime());
//                //判断通知是否已处理，若已处理，则不予处理
//                if(payRecord.getPayTime()==null){
//                    System.out.println("通知微信后台");
//                    payRecord.setPayTime(new Date());
//                    String phone=payRecord.getPhone();
//                    AppCustomer appCustomer=appCustomerService.getByPhone(phone);
//                    float balance=appCustomer.getBalance();
//                    balance+=Float.valueOf(map.get("total_fee"))/100;
//                    appCustomer.setBalance(balance);
//                    appCustomerService.update(appCustomer);
//                    payRecordService.update(payRecord);
//                    String notifyStr = XMLUtil.setXML("SUCCESS", "");
//                    writer.write(notifyStr);
//                    writer.flush();
//                }
//            }
//        }
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退款接口，会将金额退还给用户，该功能还在施工中", httpMethod = "post")
    public ReturnMessage refund(@RequestParam("uid") Integer uid,@RequestParam("oid") Integer oid){
        //TODO:退款操作
        return new ReturnMessage(200, "收到退款请求，客服将会尽快审核");
    }

}