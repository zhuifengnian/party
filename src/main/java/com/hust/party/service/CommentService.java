package com.hust.party.service;


import com.hust.party.common.Page;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment>{
    List<Comment> getCommnet(Integer enterpriseId);
}
