package com.hust.party.serviceimpl;

import com.hust.party.common.Const;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.dao.*;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.pojo.OrderUser;
import com.hust.party.pojo.Orders;
import com.hust.party.service.OrderUserService;
import com.hust.party.util.OrdersUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.OrderActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 14:13
 */
@Service
public class OrderUserServiceImpl extends AbstractBaseServiceImpl<OrderUser> implements OrderUserService {

    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Override
    public BaseMapper<OrderUser> getDao() {
        return orderUserMapper;
    }

    @Override
    public List<Integer> selectOrdersByUid(Integer uid, Page page) {

        return orderUserMapper.selectOrdersByUid(uid, page);
    }

    @Override
    public Integer selectUserCnt(Integer order_id) {

        return orderUserMapper.selectUserCnt(order_id);
    }

    @Override
    public List<OrderUser> selectOrderUserByUidAndOid(Integer uid, Integer oid) {
        return orderUserMapper.selectOrderUserByUidAndOid(uid, oid);
    }

    @Override
    public PageInfo<OrderActivityVO> selectOrders(Integer uid, Integer orderStatus, Page page) {
        //分页准备
        PageInfo<OrderActivityVO> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getPageNumber());
        pageInfo.setPageSize(page.getPageSize());

        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();

        switch(orderStatus){
            //全部
            case Const.ORDER_LIST_STATUS_ALL:
                orderActivityVOs = orderListAll(uid, page);
                break;
            //拼单中
            case Const.ORDER_LIST_STATUS_ENGAGING:
                orderActivityVOs = orderListEngaging(uid, page);
                break;
            //待消费
            case Const.ORDER_LIST_STATUS_WAIT_CONSUME:
                orderActivityVOs = orderListWaitConsume(uid, page);
                break;
            //已完成
            case Const.ORDER_LIST_STATUS_FINISH:
                orderActivityVOs = orderListFinish(uid, page);
                break;
            //退款
            case Const.ORDER_LIST_STATUS_DRAWBACKING:
                orderActivityVOs = orderListDrawbacking(uid, page);
                break;
        }
        //拿到用户id,再拿到其下指定订单
        dealOrderActivityVO(orderActivityVOs);
        pageInfo.setRows(orderActivityVOs);
        return pageInfo;
    }

    @Override
    public List<Integer> getUserId(Integer order_id) {
        return orderUserMapper.getUserId(order_id);
    }

    @Override
    public ReturnMessage judgeUserInOrder(Orders orders, Integer uid) {
        //判断订单是否过期，当已经是过期的状态时，直接返回；当订单的状态还不是过期时，再拿去活动时间进行比对判断
        if(orders.getState() == Const.ORDER_STATUS_EXCEED_TIME){
            return new ReturnMessage(201, "订单已经过期");
        }
        Integer activityId = orders.getActivityId();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        //获取当前时间，默认超过1天算超时
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.DAY_OF_MONTH, 1);

        Date time = calendar.getTime();
        Date arriveTime = activity.getArriveTime();
        if(arriveTime.before(time)){    //当当前时间超过活动结束时间，不允许用户再参与拼团
            orders.setState(Const.ORDER_STATUS_EXCEED_TIME);
            ordersMapper.updateByPrimaryKey(orders);
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

        List<OrderUser> orderUsers = orderUserMapper.selectOrderUserByUidAndOid(uid, orders.getId());
        if(orderUsers.size() > 0){
            return new ReturnMessage(201, "用户已经在此订单中，不能重复参与");
        }

        //走到这说明检验成功，可以让该用户参与,生成随机插入口令存在session中，用于做插入校验。默认30分钟有效
//        String token = UUID.randomUUID().toString();
//        session.setAttribute("" + oid + uid, token);
//        String sessionId = session.getId();
        return new ReturnMessage(200, "用户校验通过，可以参与订单");
    }

    /**
     * 退款中的实现
     */
    private List<OrderActivityVO> orderListDrawbacking(Integer uid, Page page) {
        List<OrderActivityVO> orderActivityVOs;
        orderActivityVOs = orderUserMapper.selectDrwabackingOrders(uid, Const.ORDER_STATUS_CANCEL, Const.USER_MONEY_NOT_TRANSFER_TO_ENTRERPRISE, page);
        return orderActivityVOs;
    }

    /**
     * 已消费订单实现
     */
    private List<OrderActivityVO> orderListFinish(Integer uid, Page page) {
        List<OrderActivityVO> orderActivityVOs;
        orderActivityVOs = orderUserMapper.selectOrders(uid, Const.ORDER_STATUS_HAS_CONSUME, page);
        return orderActivityVOs;
    }

    /**
     * 等待消费订单实现，包括达到最小人数的状态和人数已满的状态
     */
    private List<OrderActivityVO> orderListWaitConsume(Integer uid, Page page) {
        List<OrderActivityVO> orderActivityVOs;
        orderActivityVOs = orderUserMapper.selectWaitConsumeOrders(uid,Const.ORDER_STATUS_REACH_LEAST_PEOPLE,Const.ORDER_STATUS_HAS_FULL, page);
        return orderActivityVOs;
    }

    /**
     * 正在拼单订单实现
     */
    private List<OrderActivityVO> orderListEngaging(Integer uid, Page page) {
        List<OrderActivityVO> orderActivityVOs;
        orderActivityVOs = orderUserMapper.selectOrders(uid,Const.ORDER_STATUS_ENGAGING, page);
        return orderActivityVOs;
    }

    /**
     * 列出所有订单
     */
    private List<OrderActivityVO> orderListAll(Integer uid, Page page) {
        List<OrderActivityVO> orderActivityVOs;
        orderActivityVOs = orderUserMapper.selectOrders(uid, null, page);
        return orderActivityVOs;
    }

    /**
     * 对订单列表需要显示的数据的处理
     */
    private void dealOrderActivityVO(List<OrderActivityVO> orderActivityVOs) {

        for(OrderActivityVO orderActivityVO: orderActivityVOs){
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
}