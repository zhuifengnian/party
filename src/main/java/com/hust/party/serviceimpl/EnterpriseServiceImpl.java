package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.EnterpriseMapper;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.EnterpriseService;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterpriseInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * <br/>
 * fan 2018/5/14 15:15
 */
@Service
public class EnterpriseServiceImpl extends AbstractBaseServiceImpl<Enterprise> implements EnterpriseService {
    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public BaseMapper<Enterprise> getDao() {
        return enterpriseMapper;
    }


    @Override
    public Integer selectUserByChatId(String chat_id) {
        return enterpriseMapper.selectUserByChatId(chat_id);
    }

    @Override
    public List<AllOrderVO> getQOrder(Integer eid, Page page) {
        return enterpriseMapper.getQOrder(eid,page);
    }

    @Override
    public Integer getQOderCount(Integer eid) {
        return enterpriseMapper.getQOderCount(eid);
    }

    @Override
    public List<AllOrderVO> getYOrder(Integer eid, Page page) {
        return enterpriseMapper.getYOrder(eid,page);
    }

    @Override
    public Integer getYOrderCount(Integer eid) {
        return enterpriseMapper.getYOrderCount(eid);
    }

    @Override
    public List<AllOrderVO> getNoOrder(Integer eid, Page page) {
        return enterpriseMapper.getNoOrder(eid,page);
    }

    @Override
    public Integer getNoOrderCount(Integer eid) {
        return enterpriseMapper.getNoOrderCount(eid);
    }

    @Override
    public List<AllOrderVO> getAllOrder(Integer eid, Page page) {
        return enterpriseMapper.getAllOrder(eid,page);
    }

    @Override
    public Integer getAllOrderCount(Integer eid) {
        return enterpriseMapper.getAllOrderCount(eid);
    }

    @Override
    public List<EnterpriseActivityVo> getAllActivity(Integer eid, Page page) {
        return enterpriseMapper.getAllActivity(eid,page);
    }

    @Override
    public Integer getAllActivityCount(Integer eid) {
        return enterpriseMapper.getAllActivityCount(eid);
    }

    @Override
    public EnterpriseInfoVO selectEnterpriseInfo(Integer eid) {
        return enterpriseMapper.selectEnterpriseInfo(eid);
    }

    @Override
    public  List<AllOrderVO>   getNewOrders(Integer eid, Timestamp t, Timestamp t1, Page page) {
        return enterpriseMapper.getNewOrders(eid,t,t1,page);
    }

    @Override
    public int getNewCount(Integer eid, Timestamp t, Timestamp t1) {
        return enterpriseMapper.getNewCount(eid,t,t1);
    }

    @Override
    public  List<AllOrderVO>   getPayOrders(Integer eid, Timestamp t, Timestamp t1, Page page) {
        return enterpriseMapper.getPayOrders(eid,t,t1,page);
    }


    @Override
    public int getPayCount(Integer eid, Timestamp t, Timestamp t1) {
        return enterpriseMapper.getPayCount(eid,t,t1);
    }

    @Override
    public List<AllOrderVO> getEnterpriseQOrders(Integer eid, Page page) {
        return enterpriseMapper.getEnterpriseQOrders(eid,page);
    }

    @Override
    public Integer getEnterpriseQOrdersCount(Integer eid) {
        return enterpriseMapper.getEnterpriseQOrdersCount(eid);
    }

}