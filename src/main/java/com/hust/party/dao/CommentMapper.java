package com.hust.party.dao;

import com.hust.party.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper  extends BaseMapper<Comment>{
  List<Comment> getCommnet(Integer enterpriseId);
}