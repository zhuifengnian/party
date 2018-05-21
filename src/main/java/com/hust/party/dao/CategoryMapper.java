package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Category;
import com.hust.party.vo.CategoryActivityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category>{
    /**
     * 根据类型id获得所有的activity
     */
    List<CategoryActivityVO> getActivityByCid(@Param("cid")Integer cid, @Param("page") Page page);
    Integer getActivityByCidCnt(@Param("cid")Integer cid);

}