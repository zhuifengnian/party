package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.*;
import com.hust.party.common.PageInfo;
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
    @Autowired
    private CategoryService categoryService;
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
    public ReturnMessage getCategoryActivity(@RequestParam("name") String name,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        PageInfo<PerenceActivityVO> pageinfo = new PageInfo<PerenceActivityVO>();
        pageSize=10;
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        if(name.equals("推荐")){
            List<PerenceActivityVO> lists=new ArrayList<>();
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
        }
        else if(name.equals("其它")){
            List<PerenceActivityVO> lists=new ArrayList<>();
           List<Activity> list=  activityService.getQitaActivity(page);
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
        }
        else if(name!=null) {
    Activity activity = new Activity();
    Category category = new Category();
    category.setName(name);
    List<Category> list = categoryService.select(category, null);
    if(list.size()!=0) {
        activity.setState(1);
        activity.setCategory(list.get(0).getId());
      List <Activity> list1=  activityService.select(activity, page);
      List<PerenceActivityVO>list2=new ArrayList<>();
        int count = activityService.selectCount(activity);
        pageinfo.setTotal(count);
        for(int i=0;i<list1.size();i++){
            PerenceActivityVO perenceActivityVO=new PerenceActivityVO();
            ReflectUtil.copyProperties(perenceActivityVO, list1.get(i));
            perenceActivityVO.setSoldNumber(0);
            list2.add(perenceActivityVO);
        }
        pageinfo.setRows(list2);


    }
}
        //  List<Activity> list = activityService.getEnterpriseActivity(eid);
        return new ReturnMessage(200, pageinfo);
    }

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
