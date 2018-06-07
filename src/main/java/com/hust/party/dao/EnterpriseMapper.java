package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnterpriseMapper  extends BaseMapper<Enterprise>{
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