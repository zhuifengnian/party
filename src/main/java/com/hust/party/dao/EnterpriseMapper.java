package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Enterprise;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterpriseInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface EnterpriseMapper  extends BaseMapper<Enterprise>{

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
     * 提取企业自己取消订单
     * @param eid
     * @param page
     * @return
     */
    List<AllOrderVO>  getEnterpriseQOrders(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getEnterpriseQOrdersCount(Integer eid);

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
    List<EnterpriseActivityVo> getAllActivity(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getAllActivityCount(Integer eid);

    /**
     * 获取企业详细信息
     * @param eid
     * @return
     */
    EnterpriseInfoVO selectEnterpriseInfo(Integer eid);

    /**
     * 提取企业今日新接订单
     * @param eid
     * @param t
     * @param t1
     * @param page
     * @return
     */
    List<AllOrderVO>   getNewOrders(@Param("eid") Integer eid, @Param("d") Timestamp t, @Param("t") Timestamp t1, @Param("page") Page page);
    int getNewCount(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1);

    /**
     * 提取企业今日消费订单
     * @param eid
     * @param t
     * @param t1
     * @param page
     * @return
     */

    List<AllOrderVO>   getPayOrders(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1, @Param("page") Page page);
    int getPayCount(@Param("eid") Integer eid,@Param("d") Timestamp t,@Param("t") Timestamp t1);
}