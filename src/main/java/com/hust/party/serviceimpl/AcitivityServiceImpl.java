package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import com.hust.party.vo.PerenceActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


}
