package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.ActivityPictureMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.pojo.ActivityPicture;
import com.hust.party.service.ActivityPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/5/26.
 */
@Service
public class ActivityPictureServiceImpl extends  AbstractBaseServiceImpl<ActivityPicture> implements ActivityPictureService {
@Autowired
private ActivityPictureMapper activitypictureMapper;
    @Override
    public BaseMapper<ActivityPicture> getDao() {
        return activitypictureMapper;
    }

    @Override
    public List<String> getAllPicture(Integer activityId) {
        return activitypictureMapper.getAllPicture(activityId);
    }
}
