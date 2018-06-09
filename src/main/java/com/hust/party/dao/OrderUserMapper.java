package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.OrderUser;
import com.hust.party.pojo.Orders;
import com.hust.party.vo.OrderActivityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderUserMapper  extends BaseMapper<OrderUser>{

    List<Integer> selectOrdersByUid(@Param("uid")Integer uid, @Param("page") Page page);

    /**
     * 根据订单id，返回其下用户数量
     * @param oid
     */
    Integer selectUserCnt(Integer oid);

    /**
     * 根据用户id和订单id，获取用户在那个订单下的情况
     * @param uid
     * @param oid
     */
    List<OrderUser> selectOrderUserByUidAndOid(@Param("uid")Integer uid, @Param("oid")Integer oid);

    /**
     * 根据用户及订单的状态选择所需订单
     */
    List<OrderActivityVO> selectOrders(@Param("uid") Integer uid, @Param("orderStatus") Integer orderStatus, @Param("page") Page page);

    /**
     * 列出等待消费的订单，包括达到最小人数和人数已满的情况，是selectOrders的特殊情况
     */
    List<OrderActivityVO> selectWaitConsumeOrders(@Param("uid") Integer uid,@Param("orderStatusReachLeastPeople") Integer orderStatusReachLeastPeople,
                                                  @Param("orderStatusHasFull") Integer orderStatusHasFull, @Param("page") Page page);

    /**
     * 列出正在退款的订单，因为涉及user_money中的退款状态，所以需要单独拿出来处理
     */
    List<OrderActivityVO> selectDrwabackingOrders(@Param("uid") Integer uid,@Param("orderStatusCancel") Integer orderStatusCancel,
             @Param("userMoneyNotTransferToEntrerprise") Integer userMoneyNotTransferToEntrerprise, @Param("page") Page page);
    /**
     * 获取user_id列表
     *
     */
    List<Integer> getUserId(Integer order_id);
}