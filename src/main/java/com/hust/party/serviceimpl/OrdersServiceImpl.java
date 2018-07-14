package com.hust.party.serviceimpl;

import com.hust.party.common.Const;
import com.hust.party.common.ReturnMessage;
import com.hust.party.dao.*;
import com.hust.party.exception.ApiException;
import com.hust.party.pojo.*;
import com.hust.party.service.OrdersService;
import com.hust.party.service.UserForceService;
import com.hust.party.util.OrdersUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.*;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 14:12
 */
@Service
public class OrdersServiceImpl extends AbstractBaseServiceImpl<Orders>implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private UserMoneyMapper userMoneyMapper;
    @Autowired
    private UserForceService userForceMapper;
    @Override
    public BaseMapper<Orders> getDao() {
        return ordersMapper;
    }

    Logger mLogger = Logger.getLogger(OrdersServiceImpl.class);

    @Override
    public OrderShareVO getOrderDetailVO(Integer oid) {
        OrderShareVO orderShareVO = new OrderShareVO();
        List<OrderShareUserVO> orderShareUserVOS = orderUserMapper.selectOrderShareUserVo(oid);
        OrderActivityVO orderActivityVo = ordersMapper.getOrderActivityVo(oid);
        dealOrderActivityVO(orderActivityVo);
        orderShareVO.setOrder(orderActivityVo);

        orderShareVO.setUsers(orderShareUserVOS);
        return orderShareVO;
    }

    @Override
    public ReturnMessage engageOrder(Integer uid, Orders orders) {
        //在orderuser里判断用户是否已经参与该订单，若没有，才允许参与
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(orders.getId());
        orderUser.setUserId(uid);
        orderUser.setState(Const.ORDER_USER_STATUS_ACTIVATE);   //插入订单时，默认有效
        //所有插入条件以满足，可以参与订单
        orderUserMapper.insert(orderUser);
        int orderUserId = orderUser.getId();

        Integer activityId = orders.getActivityId();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        //记录用户支付数据,插入数据到user_money表中
        //TODO:这里因为不涉及优惠券和优惠套餐，所以默认用户的支付价格就是活动的单价
        BigDecimal preferentialPrice = activity.getPreferentialPrice();

        UserMoney userMoney = new UserMoney();
        userMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);
        userMoney.setMoney(preferentialPrice);
        userMoney.setUserorderId(orderUserId);
        userMoneyMapper.insert(userMoney);

        //获取插入之后的当前订单人数
        Integer userCnt = orderUserMapper.selectUserCnt(orders.getId());

        Integer containPeople = activity.getContainPeople();    //活动最大人数
        Integer atleastPeople = activity.getMinuPeople();    //活动最少人数

        //参团人的团力值加2
        int i = userForceMapper.insertCommonUserForce(uid);
        if(i != 1){
            mLogger.info("团力值添加失败");
        }

        //插入完成后判断订单总的状态是否达到了最大容纳人数，达到后，订单状态改为人数已满
        if(userCnt >= containPeople){
            orders.setState(Const.ORDER_STATUS_HAS_FULL);//订单状态改为人数已满
            ordersMapper.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "插入成功，插入后的订单已达人数上限");
        }

        //再判断是否达到了订单的最小人数，当达到了最小人数后，订单状态改为可以支付
        if(userCnt >= atleastPeople){
            orders.setState(Const.ORDER_STATUS_REACH_LEAST_PEOPLE); //设置订单状态为达到最小人数，可以给商家付款
            ordersMapper.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "插入成功，插入后的订单已达最小人数，可以开始支付");
        }

        //走到最后，说明还未满足订单的最小人数
        return new ReturnMessage(200, "插入成功");
    }

    @Override
    public ReturnMessage insertOrder(Integer aid, Integer uid) {
        Date createTime = new Date(System.currentTimeMillis());

        //先在订单表中生成一条订单
        Orders order = new Orders();
        order.setActivityId(aid);
        order.setState(Const.ORDER_STATUS_ENGAGING);    //新插入的订单默认处于正在拼团状态
        String qrCode = java.util.UUID.randomUUID().toString();
        order.setQrCode(qrCode);  //生成二维码随机字符串

        Activity activity = activityMapper.selectByPrimaryKey(aid);
        Integer eid = activity.getEnterpriseId();

        order.setCreateTime(createTime);
        order.setEnterpriseId(eid);        //设置订单的企业id，根据活动获得
        ordersMapper.insert(order);
        int oid = order.getId();


        //生成订单的用户也需要记录在order_user表中
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderId(oid);
        orderUser.setState(Const.ORDER_USER_STATUS_ACTIVATE);
        orderUser.setUserId(uid);
        orderUser.setCreatTime(createTime);
        orderUserMapper.insert(orderUser);
        int orderUserId = orderUser.getId();

        //记录用户支付数据,插入数据到user_money表中
        //TODO:这里因为不涉及优惠券和优惠套餐，所以默认用户的支付价格就是活动的单价
        BigDecimal preferentialPrice = activity.getPreferentialPrice();

        UserMoney userMoney = new UserMoney();
        userMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);
        userMoney.setMoney(preferentialPrice);
        userMoney.setUserorderId(orderUserId);
        userMoneyMapper.insert(userMoney);

        //开团人的团力值加10
        int i = userForceMapper.insertCommonUserForce(uid);
        if(i != 1){
            mLogger.info("团力值添加失败");
        }

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

    @Override
    public ReturnMessage cancelOrderUser(Integer uid, Orders orders) {

        //判断该订单下是否存在该用户
        List<OrderUser> orderUsers = orderUserMapper.selectOrderUserByUidAndOid(uid, orders.getId());

        if(orderUsers.size() <= 0){
            throw new ApiException(201, "该用户不在此订单下，不能执行取消");
        }
        OrderUser orderUser = orderUsers.get(0);

        //当用户已经将钱转入到商家账户时，不给退款
        //拿到存在数据库中的usermoney对象
        UserMoney tmpUserMoney = new UserMoney();
        tmpUserMoney.setUserorderId(orderUser.getId());
        tmpUserMoney.setState(Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE);   //只有当用户的那笔钱还没被取出以及没消费时，才可取出

        List<UserMoney> userMoneys = this.userMoneyMapper.select(ReflectUtil.generalMap(tmpUserMoney));
        UserMoney userMoney = userMoneys.get(0);
        if(userMoney == null){
            throw new ApiException(201, "数据发生错误，无法取出");
        }

        //将user_money中记录的金额退还
        BigDecimal drawbackMoney = userMoney.getMoney();
        //将user_money中的数据的状态置为已取出
        userMoney.setState(Const.USER_MONEY_DRAWBACK);
        userMoneyMapper.updateByPrimaryKey(userMoney);

        //再取消orderuser，即修改order_user的状态为cancel
        orderUser.setState(Const.ORDER_USER_STATUS_CANSEL);
        orderUserMapper.updateByPrimaryKey(orderUser);

        //需要再修改order中的数据
        //先判断人数是否不满足最低要求
        Integer userCnt = orderUserMapper.selectUserCnt(orders.getId());
        Activity activity = activityMapper.selectByPrimaryKey(orders.getActivityId());
        Integer minuPeople = activity.getMinuPeople();

//        //退出时将支付口令从session中移除
//        session.removeAttribute("" + oid + uid);

        if(userCnt <= minuPeople){
            orders.setState(Const.ORDER_STATUS_ENGAGING);   //当人数不满足最低时，订单状态从可支付变为正在参与
            ordersMapper.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "退出订单成功，本次退出后订单不再满足最小人数");
        }

        //判断订单是否已经取消，这种情况会在订单只剩一个人的时候取消
        if(userCnt <= 0){
            orders.setState(Const.ORDER_STATUS_CANCEL);
            ordersMapper.updateByPrimaryKey(orders);
            return new ReturnMessage(200, "退出订单成功，本次退出后订单将撤销");
        }

        return new ReturnMessage(200, "退出订单成功");

    }

    /**
     * 对订单列表需要显示的数据的处理
     */
    private void dealOrderActivityVO(OrderActivityVO orderActivityVO) {

        Integer oid = orderActivityVO.getOid();     //oid在获取对象时已经放入
        Integer status = orderActivityVO.getStatus();   //订单状态在查询时也已完成
        orderActivityVO.setStatusName(OrdersUtil.getStateName(status));
        Orders orders = ordersMapper.selectByPrimaryKey(oid);
        orderActivityVO.setQrCode(orders.getQrCode());  //二维码

        Integer activityId = orders.getActivityId();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        //将数据封装到vo类中
        ReflectUtil.copyProperties(orderActivityVO, activity);

        orderActivityVO.setAid(activity.getId());

        //获取统计人数
        OrderUser tmpOrderUser2 = new OrderUser();
        tmpOrderUser2.setOrderId(oid);
        tmpOrderUser2.setState(Const.ORDER_USER_STATUS_ACTIVATE);
        int userCnt = orderUserMapper.selectCount(tmpOrderUser2);
        orderActivityVO.setNum(userCnt);

        //补充订单中商家相关的数据
        Integer enterpriseId = activity.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        ActivityEnterpriseVo activityEnterpriseVo = new ActivityEnterpriseVo();

        activityEnterpriseVo.setEnterpriseName(enterprise.getNickname());
        activityEnterpriseVo.setEnterpriseId(enterpriseId);
        activityEnterpriseVo.setEnterprisePhone(enterprise.getLeadPhone());
        activityEnterpriseVo.setAvatarurl(enterprise.getAvatarurl());

        orderActivityVO.setActivityEnterpriseVo(activityEnterpriseVo);

        //TODO:真实价格暂时使用活动优惠价格
        orderActivityVO.setRealPrice(activity.getPreferentialPrice());
    }
}