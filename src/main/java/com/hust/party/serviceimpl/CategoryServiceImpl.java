package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.CategoryMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Category;
import com.hust.party.service.ActivityService;
import com.hust.party.service.CategoryService;
import com.hust.party.vo.CategoryActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/5/21.
 */
@Service
public class CategoryServiceImpl extends AbstractBaseServiceImpl<Category> implements CategoryService {
   @Autowired
   private CategoryMapper categoryMapper;
    @Override
    public BaseMapper<Category> getDao() {
        return categoryMapper;
    }

    @Override
    public List<CategoryActivityVO> selectActivityByCategory(Integer cid, Page page) {
        return categoryMapper.getActivityByCid(cid, page);
    }

    @Override
    public Integer getActivityByCidCnt(Integer cid) {
        return categoryMapper.getActivityByCidCnt(cid);
    }
}
