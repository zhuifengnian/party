package com.hust.party.service;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 15:12
 */
public interface EnterpriseService extends BaseService<Enterprise>{
    Integer selectUserByChatId(String chat_id);
    List<AllOrderVO> getQOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getQOderCount(Integer eid);
    List<AllOrderVO> getYOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getYOrderCount(Integer eid);
    List<AllOrderVO>  getNoOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getNoOrderCount(Integer eid);
    List<AllOrderVO>  getAllOrder(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getAllOrderCount(Integer eid);
    List<EnterpriseActivityVo> getAllActivity(@Param("eid") Integer eid, @Param("page") Page page);
}