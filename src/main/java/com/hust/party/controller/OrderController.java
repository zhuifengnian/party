package com.hust.party.controller;

import com.hust.party.common.Const;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiExpection;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.OrderActivityVO;
import com.hust.party.vo.UserOrderVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * OrderController<br/>
 * fan 2018/5/14 13:49
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService mOrdersService;
    @Autowired
    private OrderUserService orderUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;

    @ApiOperation(value =  "生成订单", notes = "发起人生成订单到数据库")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/insertOrder", method = RequestMethod.POST)
    public ReturnMessage insertOrder(@RequestParam("aid")Integer aid,@ApiParam(required = true, name = "用户chat_id",
            value = "用户chat_id") @RequestParam("chat_id") String chat_id){
        //先判断当前订单活动是否已经达到最大人数，当达到最大人数时，不允许再插入

        Orders order = new Orders();
        order.setActivityId(aid);
        int oid = mOrdersService.insert(order);
        //从user表中查出user
        Integer uid = userService.selectUserByChatId(chat_id);
        if(uid == null){
            throw new ApiExpection(201, "用户不存在，无法生成订单");
        }

        //生成订单的用户也需要记录在order_user表中
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setState(1);
        orderUser.setUserId(uid);
        int insert = orderUserService.insert(orderUser);
        return new ReturnMessage(200, insert);
    }

    @ApiOperation(value =  "其他用户参与订单", notes = "其他用户参与订单")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/engageOrder", method = RequestMethod.POST)
    public ReturnMessage engageOrder(@RequestParam("oid")Integer oid,@ApiParam(required = true, name = "用户chat_id",
            value = "用户chat_id") @RequestParam("chat_id") String chat_id){

        //先判断当前订单活动是否已经达到最大人数，当达到最大人数时，不允许再参与
        Orders orders = mOrdersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiExpection(201, "所给订单id不存在");
        }
        //从user表中查出user
        Integer uid = userService.selectUserByChatId(chat_id);
        if(uid == null){
            throw new ApiExpection(201, "用户不存在，无法生成订单");
        }

        //在orderuser里先判断用户是否已经参与该订单，若没有，才允许参与
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setUserId(uid);
        int exist = orderUserService.selectCount(orderUser);
        if(exist > 0){
            return new ReturnMessage(201, "用户已经存在在订单中，不需要再参与");
        }

        //判断订单是否还可以再参与，而不是人数已满或过期等其他状态
        Integer state = orders.getState();
        if(state != Const.ORDER_STATUS_CAN_PAY){
            String msg = "";
            switch(state){
                case Const.ORDER_STATUS_EXCEED_TIME:
                    msg = "订单超时";
                    break;
                case Const.ORDER_STATUS_HAS_FULL:
                    msg = "人数已满";
            }
            return new ReturnMessage(201, "该订单不能再让用户," + msg);
        }
        //所有插入条件以满足，可以参与订单
        orderUser.setState(1);

        int insert = orderUserService.insert(orderUser);
        return new ReturnMessage(200, "插入成功" + insert);
    }



    @ApiOperation(value = "根据用户的openid，查询该用户订单列表", notes = "根据用户的openid，查询订单列表")
    @ResponseBody
    @RequestMapping(value="/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
           @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber, @RequestParam("chat_id") String chat_id){
        //分页准备
        PageInfo<Integer> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNumber);
        pageInfo.setPageSize(pageSize);
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        //拿到用户id,再拿到其下所有订单
        Integer uid = userService.selectUserByChatId(chat_id);
        List<Integer> integers = orderUserService.selectOrdersByUid(uid, page);       //该用户下的所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        //去order表找activity
        for(Integer i: integers){
            Orders order = mOrdersService.selectByPrimaryKey(i);
            if(order == null){
                continue;
            }

            //补充订单中活动相关的数据
            Integer activityId = order.getActivityId();

            Activity activity = activityService.selectByPrimaryKey(activityId);
            OrderActivityVO orderActivityVO = new OrderActivityVO();

            ReflectUtil.copyProperties(orderActivityVO, activity);

            orderActivityVO.setAid(activity.getId());
            orderActivityVO.setOid(order.getId());

            //获取统计人数
            Integer userCnt = orderUserService.selectUserCnt(order.getId());
            orderActivityVO.setNum(userCnt);

            //补充订单中商家相关的数据
            Integer enterpriseId = activity.getEnterpriseId();
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(enterpriseId);
            orderActivityVO.setEnterpriceName(enterprise.getName());
            orderActivityVO.setEnid(enterpriseId);
            orderActivityVO.setPhone(enterprise.getPhone());        //商家电话

            //判断订单状态
            Integer containPeople = activity.getContainPeople();    //活动最大人数
            Integer atleastPeople = activity.getMinuPeople();    //活动最少人数

            //当订单还未超时时，会有三种状态，当超时时，订单状态改为超时状态
            if(userCnt < atleastPeople){
                orderActivityVO.setStatus(Const.ORDER_STATUS_NOT_FULL);
            }else if(userCnt <= containPeople){
                orderActivityVO.setStatus(Const.ORDER_STATUS_CAN_PAY);
            }else{
                orderActivityVO.setStatus(Const.ORDER_STATUS_HAS_FULL);
            }

            //获取当前时间，默认超过1天算超时
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            if(activity.getActivityTime().before(calendar.getTime())){
                orderActivityVO.setStatus(Const.ORDER_STATUS_EXCEED_TIME);
            }

            //TODO:优惠券跟实际价格暂未实现

            orderActivityVOs.add(orderActivityVO);
        }
        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setOrders(orderActivityVOs);
        return  new ReturnMessage(200, userOrderVO);
    }
}