package com.hust.party.controller;

import com.hust.party.common.Const;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.OrderActivityVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private EnterpriseService enterpriseService;
    @Autowired
    private UserMoneyService userMoneyService;
    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value =  "生成订单", notes = "发起人生成订单到数据库，能够调用这个接口的前提是用户已经支付了这个订单，" +
            "所以这里我们不再处理支付逻辑，只对支付和订单作记录")
    @ResponseBody
    @Transactional
    @RequestMapping(value="/insertOrder", method = RequestMethod.POST)
    public ReturnMessage insertOrder(@RequestParam("aid")Integer aid, @ApiParam(required = true, name = "uid",
            value = "uid") @RequestParam("uid") Integer uid){

        //先判断用户是否登录，只有登录获取uid后才可进行生成订单操作
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
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
        orderUser.setState(Const.ORDER_USER_STATUS_ACTIVATE);
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

        //再在payment表中记录下这笔金额（payment记录的是这笔订单需要给商家的金额，在第一个人创建订单时，会生成这样的记录
//        Payment payment = new Payment();
//    //    payment.setUserId(uid);     //这里目前记录的是创建者的id
//        payment.setOrderId(oid);
//        payment.setState(Const.PAYMENT_NOT_PAY);
//        payment.setPrice(preferentialPrice);
//        payment.setEnterpriseId(eid);
//        payment.setActivityId(aid);
//        paymentService.insert(payment);

        return new ReturnMessage(200, oid);  //成功返回订单id
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

        //判断订单是否过期，当已经是过期的状态时，直接返回；当订单的状态还不是过期时，再拿去活动时间进行比对判断
        if(orders.getState() == Const.ORDER_STATUS_EXCEED_TIME){
            return new ReturnMessage(201, "订单已经过期");
        }
        Integer activityId = orders.getActivityId();
        Activity activity = activityService.selectByPrimaryKey(activityId);
        //获取当前时间，默认超过1天算超时
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.DAY_OF_MONTH, 1);

        Date time = calendar.getTime();
        Date arriveTime = activity.getArriveTime();
        if(arriveTime.before(time)){    //当当前时间超过活动结束时间，不允许用户再参与拼团
            orders.setState(Const.ORDER_STATUS_EXCEED_TIME);
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(201, "订单已经超时，不能再让用于参与");
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

        List<OrderUser> orderUsers = orderUserService.selectOrderUserByUidAndOid(uid, oid);
        if(orderUsers.size() > 0){
            return new ReturnMessage(201, "用户已经在此订单中，不能重复参与");
        }

        //走到这说明检验成功，可以让该用户参与,生成随机插入口令存在session中，用于做插入校验。默认30分钟有效
//        String token = UUID.randomUUID().toString();
//        session.setAttribute("" + oid + uid, token);
//        String sessionId = session.getId();
        return new ReturnMessage(200, "用户校验通过，可以参与订单");
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
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "用户不存在，无法生成订单");
        }
//        //判断用户传入口令是否正确
//        HttpSession session1 = session.getSessionContext().getSession(sessionId);
//        String tokenVal = (String) session1.getAttribute("" + oid + uid);
//        if(tokenVal == null){
//            throw new ApiException(201, "所传参与口令不正确");
//        }

        //在orderuser里判断用户是否已经参与该订单，若没有，才允许参与
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setUserId(uid);
        orderUser.setState(Const.ORDER_USER_STATUS_ACTIVATE);   //插入订单时，默认有效
        //所有插入条件以满足，可以参与订单
        int orderUserId = orderUserService.insert(orderUser);

        Integer activityId = orders.getActivityId();
        Activity activity = activityService.selectByPrimaryKey(activityId);

        //记录用户支付数据,插入数据到user_money表中
        //TODO:这里因为不涉及优惠券和优惠套餐，所以默认用户的支付价格就是活动的单价
        BigDecimal preferentialPrice = activity.getPreferentialPrice();

        UserMoney userMoney = new UserMoney();
        userMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);
        userMoney.setMoney(preferentialPrice);
        userMoney.setUserorderId(orderUserId);
        userMoneyService.insert(userMoney);

        //获取插入之后的当前订单人数
        Integer userCnt = orderUserService.selectUserCnt(orders.getId());

        Integer containPeople = activity.getContainPeople();    //活动最大人数
        Integer atleastPeople = activity.getMinuPeople();    //活动最少人数

        //插入完成后判断订单总的状态是否达到了最大容纳人数，达到后，订单状态改为人数已满
        if(userCnt >= containPeople){
            orders.setState(Const.ORDER_STATUS_HAS_FULL);//订单状态改为人数已满
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "插入成功，插入后的订单已达人数上限");
        }

        //再判断是否达到了订单的最小人数，当达到了最小人数后，订单状态改为可以支付
        if(userCnt >= atleastPeople){
            orders.setState(Const.ORDER_STATUS_REACH_LEAST_PEOPLE); //设置订单状态为达到最小人数，可以给商家付款
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "插入成功，插入后的订单已达最小人数，可以开始支付");
        }

        //走到最后，说明还未满足订单的最小人数
        return new ReturnMessage(200, "插入成功");
    }

    @ApiOperation(value =  "用户取消订单", notes = "用户取消订单")
    @ResponseBody
    @Transactional
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

        //判断该订单下是否存在该用户
        List<OrderUser> orderUsers = orderUserService.selectOrderUserByUidAndOid(uid, oid);

        if(orderUsers.size() <= 0){
            throw new ApiException(201, "该用户不在此订单下，不能执行取消");
        }
        OrderUser orderUser = orderUsers.get(0);

        //当用户已经将钱转入到商家账户时，不给退款
        //拿到存在数据库中的usermoney对象
        UserMoney tmpUserMoney = new UserMoney();
        tmpUserMoney.setUserorderId(orderUser.getId());
        tmpUserMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);   //只有当用户的那笔钱还没被取出以及没消费时，才可取出
        List<UserMoney> userMoneys = this.userMoneyService.select(tmpUserMoney, null);
        UserMoney userMoney = userMoneys.get(0);
        if(userMoney == null){
            throw new ApiException(201, "数据发生错误，无法取出");
        }

        //将user_money中记录的金额退还
        BigDecimal drawbackMoney = userMoney.getMoney();
        //将user_money中的数据的状态置为已取出
        userMoney.setState(Const.USER_MONEY_DRAWBACK);
        userMoneyService.updateByPrimaryKey(userMoney);

        //再取消orderuser，即修改order_user的状态为cancel
        orderUser.setState(Const.ORDER_USER_STATUS_CANSEL);
        orderUserService.updateByPrimaryKey(orderUser);

        //需要再修改order中的数据
        //先判断人数是否不满足最低要求
        Integer userCnt = orderUserService.selectUserCnt(oid);
        Activity activity = activityService.selectByPrimaryKey(orders.getActivityId());
        Integer minuPeople = activity.getMinuPeople();

//        //退出时将支付口令从session中移除
//        session.removeAttribute("" + oid + uid);

        if(userCnt <= minuPeople){
            orders.setState(Const.ORDER_STATUS_ENGAGING);   //当人数不满足最低时，订单状态从可支付变为正在参与
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "退出订单成功，本次退出后订单不再满足最小人数");
        }

        //判断订单是否已经取消，这种情况会在订单只剩一个人的时候取消
        if(userCnt <= 0){
            orders.setState(Const.ORDER_STATUS_CANCEL);
            ordersService.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "退出订单成功，本次退出后订单将撤销");
        }

        return new ReturnMessage(200, "退出订单成功");
    }

    @ApiOperation(value = "查询该用户下的各种订单列表", notes = "根据用户的uid，以及订单状态，查询该用户下的订单列表，比如全部，拼单中，待消费，已完成，退款中")
    @ResponseBody
    @RequestMapping(value="/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
           @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, @RequestParam("uid") Integer uid, @RequestParam("order_state") Integer order_state){
        //判断用户是否存在
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(201, "用户id不存在");
        }
        //分页准备
        PageInfo<OrderActivityVO> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNumber);
        pageInfo.setPageSize(pageSize);
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        //全部订单
        if(order_state == Const.ORDER_LIST_STATUS_ALL){
            orderActivityVOs = listAllOrders(uid, page);
        }
        //拼单中
        if(order_state == Const.ORDER_STATUS_ENGAGING){
            orderActivityVOs = listEngagingOrders(uid, page);
        }
        //待消费
        if(order_state == Const.ORDER_STATUS_REACH_LEAST_PEOPLE){
            orderActivityVOs = listReachListPeopleOrders(uid, page);
        }
        //已完成
        if(order_state == Const.ORDER_STATUS_HAS_CONSUME){
            orderActivityVOs = listFinishOrders(uid, page);
        }
        //退款中
        if(order_state == Const.ORDER_STATUS_ENGAGING){
        }

        pageInfo.setRows(orderActivityVOs);
        return  new ReturnMessage(200, pageInfo);
    }

    /**
     * 列出已经满足消费条件的订单
     */
    private List<OrderActivityVO> listReachListPeopleOrders(Integer uid, Page page) {
        //拿到用户id,再拿到其下所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        OrderUser tmpOrderUser = new OrderUser();
        tmpOrderUser.setUserId(uid);
        tmpOrderUser.setState(Const.ORDER_STATUS_REACH_LEAST_PEOPLE);
        List<OrderUser> orderUsers2 = orderUserService.select(tmpOrderUser, page);
        dealOrderActivityVO(orderActivityVOs, orderUsers2);

        return orderActivityVOs;
    }

    /**
     * 列出正在拼单的订单
     */
    private List<OrderActivityVO> listEngagingOrders(Integer uid, Page page) {
        //拿到用户id,再拿到其下所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        OrderUser tmpOrderUser = new OrderUser();
        tmpOrderUser.setUserId(uid);
        tmpOrderUser.setState(Const.ORDER_STATUS_ENGAGING);
        List<OrderUser> orderUsers2 = orderUserService.select(tmpOrderUser, page);
        dealOrderActivityVO(orderActivityVOs, orderUsers2);

        return orderActivityVOs;
    }

    /**
     * 列出已完成的订单
     */
    private List<OrderActivityVO> listFinishOrders(Integer uid, Page page){
        //拿到用户id,再拿到其下所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        OrderUser tmpOrderUser = new OrderUser();
        tmpOrderUser.setUserId(uid);
        tmpOrderUser.setState(Const.ORDER_STATUS_HAS_CONSUME);
        List<OrderUser> orderUsers2 = orderUserService.select(tmpOrderUser, page);
        dealOrderActivityVO(orderActivityVOs, orderUsers2);

        return orderActivityVOs;
    }

    private void dealOrderActivityVO(List<OrderActivityVO> orderActivityVOs, List<OrderUser> orderUsers2) {
        //去order表找activity
        for(OrderUser orderUser: orderUsers2){
            //补充订单中活动相关的数据
            Integer orderId = orderUser.getOrderId();
            Integer uid = orderUser.getUserId();
            Orders orders = ordersService.selectByPrimaryKey(orderId);
            Integer activityId = orders.getActivityId();
            Activity activity = activityService.selectByPrimaryKey(activityId);
            //将数据封装到vo类中
            OrderActivityVO orderActivityVO = new OrderActivityVO();

            ReflectUtil.copyProperties(orderActivityVO, activity);

            orderActivityVO.setAid(activity.getId());
            orderActivityVO.setOid(orderId);

            //获取统计人数
//            Integer userCnt = orderUserService.selectUserCnt(orderId);
            OrderUser tmpOrderUser2 = new OrderUser();
            tmpOrderUser2.setUserId(uid);
            tmpOrderUser2.setOrderId(orderId);
            int userCnt = orderUserService.selectCount(tmpOrderUser2);
            orderActivityVO.setNum(userCnt);

            //补充订单中商家相关的数据
            Integer enterpriseId = activity.getEnterpriseId();
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(enterpriseId);
            ActivityEnterpriseVo activityEnterpriseVo = new ActivityEnterpriseVo();

            activityEnterpriseVo.setEnterpriseName(enterprise.getNickname());
            activityEnterpriseVo.setEnterpriseId(enterpriseId);
            activityEnterpriseVo.setEnterprisePhone(enterprise.getLeadPhone());
            activityEnterpriseVo.setAvatarurl(enterprise.getAvatarurl());

            orderActivityVO.setActivityEnterpriseVo(activityEnterpriseVo);

            //获取订单创建时间
            orderActivityVO.setCreatTime(orderUser.getCreatTime());
            orderActivityVO.setConsumeTime(orderUser.getConsumeTime());

            //TODO:真实价格暂时
            orderActivityVO.setRealPrice(activity.getPreferentialPrice());

            //获取用户在这条订单下的状态
            orderActivityVO.setStatus(orders.getState());

            //TODO:优惠券跟实际价格暂未实现

            orderActivityVOs.add(orderActivityVO);
        }
    }

    /**
     * 列出用户所有的订单
     */
    private List<OrderActivityVO> listAllOrders(Integer uid, Page page){
        //拿到用户id,再拿到其下所有订单
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        OrderUser tmpOrderUser = new OrderUser();
        tmpOrderUser.setUserId(uid);
        List<OrderUser> orderUsers2 = orderUserService.select(tmpOrderUser, page);
        //去order表找activity
        dealOrderActivityVO(orderActivityVOs, orderUsers2);
        return orderActivityVOs;
    }
}