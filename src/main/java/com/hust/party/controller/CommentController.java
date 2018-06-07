package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Comment;
import com.hust.party.pojo.Enterprise;
import com.hust.party.pojo.User;
import com.hust.party.service.*;
import com.hust.party.vo.CommentVo;
import com.hust.party.vo.PerenceActivityVO;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class CommentController
{
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @RequestMapping(value = "/getComment", method = RequestMethod.POST)
    @ApiOperation(value = "根据商家id提取信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getComment(@RequestParam("eid") Integer eid, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=4;
        PageInfo<CommentVo> pageinfo=new PageInfo<CommentVo>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<CommentVo> lists=commentService.getEnterpriseComment(eid,page);
        pageinfo.setRows( lists);
        pageinfo.setTotal(commentService.getEnterpriseCommentCount(eid));
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/putComment", method = RequestMethod.POST)
    @ApiOperation(value = "插入评论")
    @ResponseBody
    public ReturnMessage setComment(Comment comment,@RequestParam("eid") Integer aid,@ApiParam(required = true, name = "uid", value = "活动id") @RequestParam("uid") Integer uid ){
        Activity activity=activityService.selectByPrimaryKey(aid);
        comment.setCommentTime(new Date());
        comment.setEnterpriseId(activity.getEnterpriseId());
        comment.setUserId(uid);
        int insert=commentService.insert(comment);
        return new ReturnMessage(200, insert);
    }

}
