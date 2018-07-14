package com.hust.party.controller;

import com.hust.party.common.Const;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.PageUtil;
import com.hust.party.vo.OrderActivityVO;
import com.hust.party.vo.OrderShareVO;
import com.sun.istack.internal.logging.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * OrderController<br/>
 * fan 2018/5/14 13:49
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderUserService orderUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserMoneyService userMoneyService;

    @ApiOperation(value =  "生成订单", notes = "发起人生成订单到数据库，能够调用这个接口的前提是用户已经支付了这个订单，" +
            "所以这里我们不再处理支付逻辑，只对支付和订单作记录")
    @ResponseBody
    @RequestMapping(value="/insertOrder", method = RequestMethod.POST)
    public ReturnMessage insertOrder(@RequestParam("aid")Integer aid, @ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){

        //先判断用户是否登录，只有登录获取uid后才可进行生成订单操作
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "用户id不存在，出现异常");
        }

        return ordersService.insertOrder(aid, uid);
    }

    @ApiOperation(value =  "判断用户是否已经存在在此订单中，并返回订单是否过期的结果", notes = "前端先根据这个接口的返回值判断，" +
            "当此订单中不存在这个用户时才可以参与订单，\n否则当用于已经存在在这个订单时，说明已经付过款了，不需要再重新付款,由前端给出提示\n" +
            "还需要判断此订单是否过期")
    @ResponseBody
    @RequestMapping(value="/judgeUserInOrder", method = RequestMethod.POST)
    public ReturnMessage judgeUserInOrder(@ApiParam(required = true, name = "oid",
            value = "oid")@RequestParam("oid")Integer oid, @ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid) {
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            return new ReturnMessage(201, "订单id不存在");
        }
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            return new ReturnMessage(202, "用户id不存在");
        }

        return orderUserService.judgeUserInOrder(orders, uid);
    }

    @ApiOperation(value =  "其他用户参与订单", notes = "其他用户参与订单")
    @ResponseBody
    @RequestMapping(value="/engageOrder", method = RequestMethod.POST)
    public ReturnMessage engageOrder(@RequestParam("oid")Integer oid,@ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){

        //脏数据判断
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201, "所给订单id不存在");
        }
        //从user表中查出user
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "用户不存在，无法生成订单");
        }
        return ordersService.engageOrder(uid, orders);


    }

    @ApiOperation(value =  "用户取消订单", notes = "用户取消订单")
    @ResponseBody
    @RequestMapping(value="/cancelOrderUser", method = RequestMethod.POST)
    public ReturnMessage cancelOrderUser(@RequestParam("oid")Integer oid, @ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){
        //判断订单是否可用
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201,"所给订单id不存在");
        }
        //判断用户是否存在
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "所给用户id不存在");
        }

        return ordersService.cancelOrderUser(uid, orders);
    }

    @ApiOperation(value = "查询该用户下的各种订单列表", notes = "根据用户的uid，以及订单状态，查询该用户下的订单列表，比如全部，拼单中，待消费，已完成，退款中")
    @ResponseBody
    @RequestMapping(value="/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
           @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam("uid") Integer uid, @RequestParam("order_state") Integer order_state){
        //判断用户是否存在
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "用户id不存在");
        }
        PageInfo<OrderActivityVO> pageInfo = orderUserService.selectOrders(uid, order_state, PageUtil.setPage(pageNumber));

        return  new ReturnMessage(200, pageInfo);
    }

    @ApiOperation(value = "获取订单分享界面数据", notes = "获取订单分享界面数据")
    @ResponseBody
    @RequestMapping(value="/getShareOrder", method = RequestMethod.GET)
    public ReturnMessage getShareOrder(@RequestParam("oid") Integer oid){
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201, "所给订单id不存在");
        }
        OrderShareVO orderDetailVO = ordersService.getOrderDetailVO(oid);
        return new ReturnMessage(200, orderDetailVO);
    }
}