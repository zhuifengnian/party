package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.ActivityTag;
import com.hust.party.pojo.Enterprise;
import com.hust.party.common.PageInfo;
import com.hust.party.pojo.Orders;
import com.hust.party.service.*;

import com.hust.party.util.QiNiuUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.ActivityVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class ActivityController
{
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;
   @Autowired
   private ActivityTagService activityTagService;
    @Autowired
    private ActivityPictureService activitypictureService;
    @RequestMapping(value = "/getactivity", method = RequestMethod.POST)
    @ApiOperation(value = "根据活动id提取信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getIdActivity(@RequestParam("aid") Integer aid){

        ActivityVo activityVo =new ActivityVo();

        Activity activity = activityService.selectByPrimaryKey(aid);
        if(activity!=null) {
            List<String> list = activitypictureService.getAllPicture(activity.getId());
            List<String> tag = activityTagService.getActivityTag(activity.getId());
            activityVo.setPictures(list);
            ReflectUtil.copyProperties(activityVo, activity);
            activityVo.setOpentime(activity.getActivityTime().toString());
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(activity.getEnterpriseId());
            ActivityEnterpriseVo activityEnterpriseVo = new ActivityEnterpriseVo();
            activityEnterpriseVo.setEnterpriseId(enterprise.getId());
            activityEnterpriseVo.setAvatarurl(enterprise.getAvatarurl());
            activityEnterpriseVo.setEnterpriseName(enterprise.getName());
            activityEnterpriseVo.setEnterprisePhone(enterprise.getLeadPhone());
            activityVo.setActivityEnterpriseVo(activityEnterpriseVo);
            activityVo.setTag(tag);
        }
        return new ReturnMessage(200, activityVo);
    }

    @RequestMapping(value = "/activity/category", method = RequestMethod.POST)
    @ApiOperation(value = "根据分类提取信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getCategoryActivity(@ModelAttribute Activity activity,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){

        if(activity.getCategory()==null)
            activity.setCategory(0);
        activity.setState(1);
        PageInfo<Activity> pageinfo=new PageInfo<Activity>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( activityService.select(activity,page));
        int count=activityService.selectCount(activity);
        pageinfo.setTotal(count);
        //  List<Activity> list = activityService.getEnterpriseActivity(eid);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getActivity(@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        List<PerenceActivityVO> lists=new ArrayList<>();
       pageSize=10;
        PageInfo<PerenceActivityVO> pageinfo=new PageInfo<PerenceActivityVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<Activity> list=activityService.getAllActivity(page);

        int counts=0;
       for(int i=0;i<list.size();i++){
           Activity activity=list.get(i);
           PerenceActivityVO perenceActivityVO=new PerenceActivityVO();
           ReflectUtil.copyProperties(perenceActivityVO, activity);

             Enterprise enterprise = enterpriseService.selectByPrimaryKey(activity.getEnterpriseId());
             perenceActivityVO.setEnterpriceName(enterprise.getName());
             perenceActivityVO.setId(activity.getId());

          int count=0;
           perenceActivityVO.setSoldNumber(count);
          if(activity.getCopies()!=activity.getArriveCopies()) {
              lists.add(perenceActivityVO);
              counts++;
          }
       }
        pageinfo.setRows( lists);
        pageinfo.setTotal(counts);
      return new ReturnMessage(200, pageinfo);
    }
    @ApiOperation(value = "插入活动", notes = "插入活动到数据库")
    @ResponseBody
    @RequestMapping(value="/insertActivity", method = RequestMethod.POST)
    public ReturnMessage insertActivity( Activity activity,  @RequestParam(value = "coverfile", required = false) MultipartFile cofile,
                                        @RequestParam(value = "flyfile", required = false) MultipartFile flfile){
       String picture=null;
       String video=null;
        if(cofile!=null)
        picture=QiNiuUtil.manageFile(cofile);
        if(flfile!=null)
      video=QiNiuUtil.manageFile(flfile);
      activity.setPicture(picture);
    activity.setVideo(video);
    activity.setState(1);
        int insertNum = activityService.insert(activity);
        return new ReturnMessage(200, insertNum);

    }


}
