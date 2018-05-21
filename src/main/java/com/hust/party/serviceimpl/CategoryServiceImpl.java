package com.hust.party.serviceimpl;

import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.CategoryMapper;
import com.hust.party.pojo.Category;
import com.hust.party.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
