package com.hust.party.controller;

import com.hust.party.common.Const;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.vo.OrderActivityVO;
import com.hust.party.vo.UserOrderVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderController<br/>
 * fan 2018/5/14 13:49
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderUserService orderUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;

    @ApiOperation(value = "插入订单", notes = "插入订单到数据库")
    @ResponseBody
    @RequestMapping(value="/insertOrder", method = RequestMethod.POST)
    public ReturnMessage insertOrder(@RequestParam("aid")Integer aid, @RequestParam("chat_id") String chat_id){
        Order order = new Order();
        order.setActivityId(aid);
        int oid = orderService.insert(order);
        //从user表中查出user
        Integer uid = userService.selectUserByChatId(chat_id);
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setStatus(1);
        orderUser.setUserId(uid);
        int insert = orderUserService.insert(orderUser);
        return new ReturnMessage(200, insert);
    }

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表")
    @ResponseBody
    @RequestMapping(value="/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
           @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber, @RequestParam("chat_id") String chat_id){
        //拿到用户id,再拿到其下所有订单
        Integer uid = userService.selectUserByChatId(chat_id);
        List<Integer> integers = orderUserService.selectOrdersByUid(uid);       //该用户下的所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        //去order表找activity
        for(Integer i: integers){
            Order order = orderService.selectByPrimaryKey(i);
            if(order == null){
                continue;
            }
            Integer activityId = order.getActivityId();

            Activity activity = activityService.selectByPrimaryKey(activityId);
            OrderActivityVO orderActivityVO = new OrderActivityVO();

            try {
                PropertyUtils.copyProperties(orderActivityVO, activity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            orderActivityVO.setAid(activity.getId());
            orderActivityVO.setOid(order.getId());

            //获取统计人数
            Integer userCnt = orderUserService.selectUserCnt(order.getId());

            //判断订单状态
            Integer containPeople = activity.getContainPeople();    //活动最大人数
            Integer atleastPeople = activity.getAtleastPeople();    //活动最少人数

            //当订单还未超时时，会有三种状态，当超时时，订单状态改为超时状态
            if(userCnt < atleastPeople){
                orderActivityVO.setStatus(Const.ORDER_STATUS_NOT_FULL);
            }else if(userCnt <= containPeople){
                orderActivityVO.setStatus(Const.ORDER_STATUS_CAN_PAY);
            }else{
                orderActivityVO.setStatus(Const.ORDER_STATUS_HAS_FULL);
            }

            //TODO;超时的判断
//            if(activity.getActivityTime())

            orderActivityVO.setNum(userCnt);
            //获取企业名
            Integer enterpriseId = activity.getEnterpriseId();
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(enterpriseId);
            orderActivityVO.setEnterpriceName(enterprise.getName());
            orderActivityVOs.add(orderActivityVO);
        }
        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setOrders(orderActivityVOs);
        return  new ReturnMessage(200, userOrderVO);
    }
}