package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import com.hust.party.vo.PerenceActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Service("activityService")
public class AcitivityServiceImpl extends  AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }


    @Override
    public List<PerenceActivityVO> getAllActivity(Page page) {

        List<PerenceActivityVO> perenceActivityVOS = new ArrayList<>();
        perenceActivityVOS = activityMapper.getAllActivity(page);
        return perenceActivityVOS;
    }

    @Override
    public Integer getAllActivityCount() {
        return activityMapper.getAllActivityCount();
    }



    @Override
    public   List<PerenceActivityVO>  getQitaActivity(Page page) {
        List<PerenceActivityVO> perenceActivityVOS = new ArrayList<>();
        perenceActivityVOS = activityMapper.getQitaActivity(page);
        return perenceActivityVOS;
    }

    @Override
    public Integer getQitaActivityCount() {
        return activityMapper.getQitaActivityCount();
    }

    @Override
    public List<PerenceActivityVO> getNameActivity(String name, Page page) {
        List<PerenceActivityVO> perenceActivityVOS = new ArrayList<>();
        perenceActivityVOS = activityMapper.getNameActivity(name,page);
        return perenceActivityVOS;
    }

    @Override
    public Integer getNameActivityCount(String name) {
        return activityMapper.getNameActivityCount(name);
    }

    @Override
    public List<Activity> getNowDay(Timestamp t1) {
        return activityMapper.getNowDay(t1);
    }

    @Override
    public int updateNowDay(Integer id) {
        return activityMapper.updateNowDay(id);
    }

    @Override
    public List<PerenceActivityVO> getEnterpriseAllActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseAllActivity(id,page);
    }

    @Override
    public Integer getEnterpriseAllActivityCount(Integer id) {
        return activityMapper.getEnterpriseAllActivityCount(id);
    }

    @Override
    public List<PerenceActivityVO> getEnterpriseNowActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseNowActivity(id,page);
    }

    @Override
    public Integer getEnterpriseNowActivityCount(Integer id) {
        return activityMapper.getEnterpriseAllActivityCount(id);
    }

    @Override
    public List<PerenceActivityVO> getEnterpriseDeleteActivity(Integer id, Page page) {
        return activityMapper.getEnterpriseDeleteActivity(id,page);
    }

    @Override
    public Integer getEnterpriseDeleteActivityCount(Integer id) {
        return activityMapper.getEnterpriseDeleteActivityCount(id);
    }

    @Override
    public PageInfo<PerenceActivityVO> getEnterpriseActivity(String name, Integer id, Page page) {
        PageInfo<PerenceActivityVO> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getPageNumber());
       pageInfo.setPageSize(page.getPageSize());
        if ("全部".equals(name)) {
            pageInfo.setRows(getEnterpriseAllActivity(id, page));
            pageInfo.setTotal(getEnterpriseAllActivityCount(id));
        } else if ("删除".equals(name)) {
            pageInfo.setRows(getEnterpriseDeleteActivity(id, page));
            pageInfo.setTotal(getEnterpriseDeleteActivityCount(id));
        } else if ("上线".equals(name)) {
            pageInfo.setRows(getEnterpriseNowActivity(id, page));
            pageInfo.setTotal(getEnterpriseNowActivityCount(id));
        }
        return pageInfo;
    }


}
