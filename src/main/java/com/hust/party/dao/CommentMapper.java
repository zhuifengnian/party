package com.hust.party.dao;

import com.hust.party.common.Page;
import com.hust.party.pojo.Comment;
import com.hust.party.vo.CommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper  extends BaseMapper<Comment>{

  List<CommentVo> getEnterpriseComment(@Param("eid") Integer eid,@Param("page") Page page);
 Integer getEnterpriseCommentCount(Integer eid);
}