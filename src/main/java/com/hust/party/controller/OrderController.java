package com.hust.party.controller;

import com.hust.party.common.Const;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
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
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private EnterpriseService enterpriseService;
    @Autowired
    private UserMoneyService userMoneyService;

    @ApiOperation(value =  "生成订单", notes = "发起人生成订单到数据库，能够调用这个接口的前提是用户已经支付了这个订单，" +
            "所以这里我们不再处理支付逻辑，只对支付和订单作记录")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/insertOrder", method = RequestMethod.POST)
    public ReturnMessage insertOrder(@RequestParam("aid")Integer aid, @ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){

        //先判断用户是否登录，只有登录获取uid后才可进行生成订单操作
        if(uid == null){
            throw new ApiException(201, "用户id不存在，出现异常");
        }

        Date createTime = new Date(System.currentTimeMillis());

        //先在订单表中生成一条订单
        Orders order = new Orders();
        order.setActivityId(aid);
        order.setState(Const.ORDER_STATUS_ENGAGING);    //新插入的订单默认处于正在拼团状态

        Activity activity = activityService.selectByPrimaryKey(aid);
        Integer eid = activity.getEnterpriseId();

        order.setCreateTime(createTime);
        order.setEnterpriseId(eid);        //设置订单的企业id，根据活动获得
        int oid = ordersService.insert(order);

        //生成订单的用户也需要记录在order_user表中
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setState(Const.ORDER_USER_STATUS_NOT_FULL);
        orderUser.setUserId(uid);
        orderUser.setCreatTime(createTime);
        int orderUserId = orderUserService.insert(orderUser);

        //记录用户支付数据,插入数据到user_money表中
        //TODO:这里因为不涉及优惠券和优惠套餐，所以默认用户的支付价格就是活动的单价
        BigDecimal preferentialPrice = activity.getPreferentialPrice();

        UserMoney userMoney = new UserMoney();
        userMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);
        userMoney.setMoney(preferentialPrice);
        userMoney.setUserorderId(orderUserId);
        userMoneyService.insert(userMoney);

        return new ReturnMessage(200, oid);  //成功返回订单id
    }

    /**
     * 订单支付
     * @param activity 需要支付的订单活动,里面包含需要支付的价格
     * @param uid 用户id
     * @return 返回支付是否成功
     */
    private boolean payOrder(Activity activity, Integer uid) {
        //TODO:这里处理支付逻辑，先默认返回支付成功
        return true;
    }
    @ApiOperation(value =  "判断用户是否已经存在在此订单中，并返回订单是否过期的结果", notes = "前端先根据这个接口的返回值判断，" +
            "当此订单中不存在这个用户时才可以参与订单，\n否则当用于已经存在在这个订单时，说明已经付过款了，不需要再重新付款,由前端给出提示\n" +
            "还需要判断此订单是否过期")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/judgeUserInOrder", method = RequestMethod.POST)
    public ReturnMessage judgeUserInOrder(@ApiParam(required = true, name = "oid",
            value = "oid")@RequestParam("oid")Integer oid,@ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid) {
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            return new ReturnMessage(201, "订单id不存在");
        }

        //判断订单是否过期
        if(orders.getState() == Const.ORDER_STATUS_EXCEED_TIME){
            return new ReturnMessage(201, "订单已经过期");
        }

        //判断订单是否已经消费
        if(orders.getState() == Const.ORDER_STATUS_HAS_CONSUME){
            return new ReturnMessage(201, "订单已经消费");
        }

        //判断商家是否取消活动
        if(orders.getState() == Const.ORDER_STATUS_ENTERPRISE_CANCEL){
            return new ReturnMessage(201, "订单已经取消");
        }

        //判断是否人数已满,理论上根据订单状态判断已经足够，这里可能还需要再重新判断订单人数，防止记录不到位
        if(orders.getState() == Const.ORDER_STATUS_HAS_FULL){
            return new ReturnMessage(201, "订单人数已满，不能再参与该订单");
        }




        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            return new ReturnMessage(202, "用户id不存在");
        }

        //

        OrderUser orderUser = orderUserService.selectOrderUserByUidAndOid(uid, oid);
        if(orderUser == null){
            return new ReturnMessage(200, "用户不在订单中，可以参与");
        }

        return null;
    }

    @ApiOperation(value =  "其他用户参与订单", notes = "其他用户参与订单")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/engageOrder2", method = RequestMethod.POST)
    public ReturnMessage engageOrder2(@RequestParam("oid")Integer oid,@ApiParam(required = true, name = "用户chat_id",
            value = "用户chat_id") @RequestParam("open_id") String chat_id){

        //脏数据判断
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201, "所给订单id不存在");
        }
        //从user表中查出user
        Integer uid = userService.selectUserByChatId(chat_id);
        if(uid == null){
            throw new ApiException(201, "用户不存在，无法生成订单");
        }

        //判断订单是否已经取消
        if(orders.getState() != null && orders.getState() == Const.ORDER_STATUS_CANCEL){
            return new ReturnMessage(201, "订单已经取消，无法参与");
        }

        //在orderuser里先判断用户是否已经参与该订单，若没有，才允许参与
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setUserId(uid);
        int exist = orderUserService.selectCount(orderUser);
        if(exist > 0){
            return new ReturnMessage(201, "用户已经存在在订单中，不需要再参与");
        }

        //当有人参与时，判断订单是否已经超时
        Integer activityId = orders.getActivityId();
        Activity activity = activityService.selectByPrimaryKey(activityId);

        //获取当前时间，默认超过1天算超时
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        if(activity.getArriveTime().before(calendar.getTime())){    //当当前时间超过活动结束时间，不允许用户再参与拼团
            orders.setState(Const.ORDER_STATUS_EXCEED_TIME);
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(201, "订单已经超时，不能再让用于参与");
        }

        //判断当前订单活动是否已经达到最大人数，当达到最大人数时，不允许再参与
        if(orders.getState() == Const.ORDER_STATUS_HAS_FULL){
            return new ReturnMessage(201, "当前订单人数已达最大人数，无法再继续参团");
        }

        //所有插入条件以满足，可以参与订单
        int insert = orderUserService.insert(orderUser);

        //获取插入之后的当前订单人数
        Integer userCnt = orderUserService.selectUserCnt(orders.getId());

        Integer containPeople = activity.getContainPeople();    //活动最大人数
        Integer atleastPeople = activity.getMinuPeople();    //活动最少人数

        //判断是否达到了订单的最小人数，当达到了最小人数后，订单状态改为可以支付
        if(userCnt >= atleastPeople){
            orderUser.setState(Const.ORDER_USER_STATUS_CAN_PAY);
            orderUserService.updateByPrimaryKey(orderUser);
            //TODO:通知该订单下的其他用户的订单状态改为可以支付
        }

        //插入完成后判断订单总的状态是否达到了最大容纳人数
        if(userCnt >= containPeople){
            orders.setState(Const.ORDER_STATUS_HAS_FULL);//订单状态改为人数已满
            ordersService.updateByPrimaryKey(orders);
        }

        return new ReturnMessage(200, "插入成功" + insert);
    }

    @ApiOperation(value =  "其他用户参与订单", notes = "其他用户参与订单")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/engageOrder", method = RequestMethod.POST)
    public ReturnMessage engageOrder(@RequestParam("oid")Integer oid,@ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){

        //脏数据判断
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201, "所给订单id不存在");
        }
        //从user表中查出user
        if(uid == null){
            throw new ApiException(201, "用户不存在，无法生成订单");
        }

        //判断订单是否已经取消
        if(orders.getState() != null && orders.getState() == Const.ORDER_STATUS_CANCEL){
            return new ReturnMessage(201, "订单已经取消，无法参与");
        }

        //在orderuser里判断用户是否已经参与该订单，若没有，才允许参与
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setUserId(uid);
        int exist = orderUserService.selectCount(orderUser);
        if(exist > 0){
            return new ReturnMessage(201, "用户已经存在在订单中，不需要再参与");
        }

        //当有人参与时，判断订单是否已经超时
        Integer activityId = orders.getActivityId();
        Activity activity = activityService.selectByPrimaryKey(activityId);

        //获取当前时间，默认超过1天算超时
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        if(activity.getArriveTime().before(calendar.getTime())){    //当当前时间超过活动结束时间，不允许用户再参与拼团
            orders.setState(Const.ORDER_STATUS_EXCEED_TIME);
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(201, "订单已经超时，不能再让用于参与");
        }

        //判断当前订单活动是否已经达到最大人数，当达到最大人数时，不允许再参与
        if(orders.getState() == Const.ORDER_STATUS_HAS_FULL){
            return new ReturnMessage(201, "当前订单人数已达最大人数，无法再继续参团");
        }

        //所有插入条件以满足，可以参与订单
        int insert = orderUserService.insert(orderUser);

        //获取插入之后的当前订单人数
        Integer userCnt = orderUserService.selectUserCnt(orders.getId());

        Integer containPeople = activity.getContainPeople();    //活动最大人数
        Integer atleastPeople = activity.getMinuPeople();    //活动最少人数

        //判断是否达到了订单的最小人数，当达到了最小人数后，订单状态改为可以支付
        if(userCnt >= atleastPeople){
            orderUser.setState(Const.ORDER_USER_STATUS_CAN_PAY);
            orderUserService.updateByPrimaryKey(orderUser);
            //TODO:通知该订单下的其他用户的订单状态改为可以支付
        }

        //插入完成后判断订单总的状态是否达到了最大容纳人数
        if(userCnt >= containPeople){
            orders.setState(Const.ORDER_STATUS_HAS_FULL);//订单状态改为人数已满
            ordersService.updateByPrimaryKey(orders);
        }

        return new ReturnMessage(200, "插入成功" + insert);
    }

    @ApiOperation(value =  "用户取消订单", notes = "用户取消订单")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/cancelOrderUser", method = RequestMethod.POST)
    public ReturnMessage cancelOrderUser(@RequestParam("oid")Integer oid, @ApiParam(required = true, name = "用户open_id",
            value = "用户open_id") @RequestParam("open_id") String open_id){
        //判断订单是否可用
        Orders orders = ordersService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(201,"所给订单id不存在");
        }
        //先拿到用户id
        Integer uid = userService.selectUserByChatId(open_id);
        //判断该订单下是否存在该用户
        OrderUser orderUser = orderUserService.selectOrderUserByUidAndOid(uid, oid);
        if(orderUser == null){
            throw new ApiException(201, "该用户不在此订单下，不能执行取消");
        }

        //满足条件，可以取消orderuser
        int i = orderUserService.deleteByPrimaryKey(orderUser.getId());

        //TODO:退款操作

        return new ReturnMessage(200, "取消订单成功" + i);
    }

    @ApiOperation(value = "根据用户的openid，查询该用户订单列表", notes = "根据用户的openid，查询订单列表")
    @ResponseBody
    @RequestMapping(value="/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
           @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber, @RequestParam("open_id") String chat_id){
        //分页准备
        PageInfo<OrderActivityVO> pageInfo = new PageInfo<>();
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
            Orders order = ordersService.selectByPrimaryKey(i);
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
            orderActivityVO.setLeadPhone(enterprise.getLeadPhone());        //商家电话

            //获取用户在这条订单下的状态
            //先拿到orderuser对象
            OrderUser orderUser = orderUserService.selectOrderUserByUidAndOid(uid, order.getId());
            orderActivityVO.setStatus(orderUser.getState());

            //TODO:优惠券跟实际价格暂未实现

            orderActivityVOs.add(orderActivityVO);
        }
        pageInfo.setRows(orderActivityVOs);
//        pageInfo.setTotal();
        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setOrders(orderActivityVOs);
        return  new ReturnMessage(200, pageInfo);
    }
}