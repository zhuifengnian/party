package com.hust.party.serviceimpl;

import com.hust.party.common.Const;
import com.hust.party.common.Page;
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
    public List<OrderActivityVO> selectOrders(Integer uid, Integer orderStatus, Page page) {
        List<OrderActivityVO> orderActivityVOs = new ArrayList<>();
        switch(orderStatus){
            //全部订单
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
            //退款中
            case Const.ORDER_LIST_STATUS_DRAWBACKING:
                orderActivityVOs = orderListDrawbacking(uid, page);
                break;
        }
        //拿到用户id,再拿到其下指定订单
        dealOrderActivityVO(orderActivityVOs);
        return orderActivityVOs;
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