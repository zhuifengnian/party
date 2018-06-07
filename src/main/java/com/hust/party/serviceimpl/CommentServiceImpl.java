package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.CommentMapper;
import com.hust.party.pojo.Comment;
import com.hust.party.service.CommentService;
import com.hust.party.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyue on 2018/5/16.
 */
@Service
public class CommentServiceImpl extends  AbstractBaseServiceImpl<Comment> implements CommentService {
@Autowired
   private CommentMapper commentMapper;
    @Override
    public BaseMapper<Comment> getDao() {
        return commentMapper;
    }



    @Override
    public List<CommentVo> getEnterpriseComment(Integer eid, Page page) {
        return commentMapper.getEnterpriseComment(eid,page);
    }

    @Override
    public Integer getEnterpriseCommentCount(Integer eid) {
        return commentMapper.getEnterpriseCommentCount(eid);
    }
}
