package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.ReturnMessage;
import com.hust.party.common.SolrUtil;
import com.hust.party.pojo.*;
import com.hust.party.common.PageInfo;
import com.hust.party.service.*;

import com.hust.party.util.PageUtil;
import com.hust.party.util.QiNiuUtil;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.ActivityVo;
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
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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
    @Autowired
    private  CommentService commentService;
    @RequestMapping(value = "/getactivity", method = RequestMethod.POST)
    @ApiOperation(value = "根据活动id提取信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getIdActivity(@RequestParam("aid") Integer aid){


        ActivityVo activityVo =activityService.getAcitivityId(aid);

                return new ReturnMessage(200, activityVo);
    }

    @RequestMapping(value = "/activity/category", method = RequestMethod.POST)
    @ApiOperation(value = "根据分类提取信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getCategoryActivity(@RequestParam("name") String name,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
       PageInfo<PerenceActivityVO> pageInfo=activityService.getNameActivity(name,PageUtil.setPage(pageNumber));
        return new ReturnMessage(200, pageInfo);
    }



    @ApiOperation(value = "插入活动", notes = "插入活动到数据库")
    @ResponseBody
    @RequestMapping(value="/insertActivity", method = RequestMethod.POST)
    public ReturnMessage insertActivity( Activity activity, Integer enterprise_id, @RequestParam(value = "coverfile", required = false) MultipartFile cofile,
                                        @RequestParam(value = "flyfile", required = false) MultipartFile flfile){
       String picture=null;
       String video=null;
        if(cofile!=null)
        picture=QiNiuUtil.manageFile(cofile);
        if(flfile!=null)
      video=QiNiuUtil.manageFile(flfile);

      activity.setEnterpriseId(enterprise_id);

      activity.setPicture(picture);
    activity.setVideo(video);
    activity.setState(1);
        Integer insertNum = activityService.insert(activity);
        if(insertNum!=null) {
            SolrUtil solrUtil = new SolrUtil();
            try {
                solrUtil.insertActivitySolr(insertNum, activity);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SolrServerException e) {
                e.printStackTrace();
            }
        }
        return new ReturnMessage(200, insertNum);

    }

    @ApiOperation(value = "搜索活动", notes = "搜索活动")
    @ResponseBody
    @RequestMapping(value="/searchActivity", method = RequestMethod.POST)
    public ReturnMessage searchActivity( @RequestParam("name") String name,@RequestParam("start") Integer start){
        SolrDocumentList list = null;
        SolrUtil solrUtil=new SolrUtil();
        try {
       list  = solrUtil.getActivitySolr(name,start);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        SolrDocumentList list1=fenYe(list);
        return new ReturnMessage(200, list1);

    }
    public SolrDocumentList fenYe(SolrDocumentList list) {

    //    List<PerenceActivityVO> lists=new ArrayList<>();

        for(int i=0;i<list.size();i++){
                Integer enterprise_id = Integer.parseInt(list.get(i).getFieldValue("enterprise_id").toString());
                Enterprise enterprise = enterpriseService.selectByPrimaryKey(enterprise_id);
                list.get(i).addField("enterprise_name",enterprise.getName());
        }
        return list;
    }
}
