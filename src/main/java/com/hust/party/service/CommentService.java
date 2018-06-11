package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Comment;
import com.hust.party.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService extends BaseService<Comment>{
    /**
     * 获取企业评论列表
     * @param eid
     * @param page
     * @return
     */
    List<CommentVo> getEnterpriseComment(@Param("eid") Integer eid, @Param("page") Page page);
    Integer getEnterpriseCommentCount(Integer eid);
    /**
     * 根据企业id获取评论集合
     * @param eid
     * @return
     */
    List<CommentVo> getAEnterpriseComment(@Param("eid") Integer eid);
}
