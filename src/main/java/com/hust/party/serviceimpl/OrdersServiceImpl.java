package com.hust.party.serviceimpl;

import com.hust.party.common.Const;
import com.hust.party.dao.*;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.pojo.OrderUser;
import com.hust.party.pojo.Orders;
import com.hust.party.service.OrdersService;
import com.hust.party.util.OrdersUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.OrderActivityVO;
import com.hust.party.vo.OrderShareUserVO;
import com.hust.party.vo.OrderShareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Override
    public BaseMapper<Orders> getDao() {
        return ordersMapper;
    }

    @Override
    public Integer getOrderId(Integer activityId) {
        return ordersMapper.getOrderId(activityId);
    }

    @Override
    public List<Orders> getOrder(Integer activityId) {
        return ordersMapper.getOrder(activityId);
    }

    @Override
    public List<Orders> getOrders(Map map) {
        return ordersMapper.getOrders( map);
    }

    @Override
    public List<Orders> getActivityOrder(Map map) {
        return ordersMapper.getActivityOrder(map);
    }

    @Override
    public int getActivityCount(Map map) {
        return ordersMapper.getActivityCount(map);
    }

    @Override
    public int getCount(Map map) {
        return ordersMapper.getCount(map);
    }


    @Override
    public List<Orders> getNewOrder(Integer activityId) {
        return ordersMapper.getNewOrder(activityId);
    }

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