package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Category;
import com.hust.party.vo.CategoryActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService extends BaseService<Category>{

    List<CategoryActivityVO> selectActivityByCategory(Integer cid, Page page);
    Integer getActivityByCidCnt(Integer cid);
}
