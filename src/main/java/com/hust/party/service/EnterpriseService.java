package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.pojo.Enterprise;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterprisePaymentInfoVO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 15:12
 */
public interface EnterpriseService extends BaseService<Enterprise>{
    /**
     * 根据chat_id进行查找
     * @param chat_id
     * @return
     */
    Integer selectUserByChatId(String chat_id);

    /**
     * 提取企业已取消订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO> getQOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getQOderCount(Integer eid);

    /**
     * 提取企业已消费订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO> getYOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getYOrderCount(Integer eid);

    /**
     * 提取用户未消费订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO>  getNoOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getNoOrderCount(Integer eid);

    /**
     * 提取企业所有订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO>  getAllOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getAllOrderCount(Integer eid);

    /**
     * 提取企业所有活动
     * @param eid
     * @param page
     * @return
     */
    PageInfo<EnterpriseActivityVo> getAllActivity(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getAllActivityCount(Integer eid);

    /**
     * 提取企业详细信息
     * @param eid
     * @return
     */
    EnterprisePaymentInfoVO selectEnterpriseInfo(Integer eid);

    /**
     * 提取企业今日新接订单
     * @param eid
     * @param t
     * @param t1
     * @param page
     * @return
     */
    List<AllOrderVO>  getNewOrders(@Param("eid") Integer eid, @Param("d") Timestamp t, @Param("t") Timestamp t1, @Param("page") Page page);
    int getNewCount(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1);

    /**
     * 提取企业今日消费订单
     * @param eid
     * @param t
     * @param t1
     * @param page
     * @return
     */

    List<AllOrderVO>  getPayOrders(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1, @Param("page") Page page);
    int getPayCount(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1);
    /**
     * 提取企业自己取消订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO>  getEnterpriseQOrders(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getEnterpriseQOrdersCount(Integer eid);

    /**
     * 企业订单
     * @param name
     * @param eid
     * @param d
     * @param t
     * @param page
     * @return
     */
    PageInfo<AllOrderVO> getEnterpriseOrder(String name, Integer eid, Timestamp d, Timestamp t, Page page);

}