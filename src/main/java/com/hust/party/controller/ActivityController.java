package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiExpection;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;

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
import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class ActivityController
{
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activity/{aid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据活动id提取信息", httpMethod = "GET")
    @ResponseBody
    public ReturnMessage getActivity(@ApiParam(required = true, name = "aid", value = "活动id") @PathVariable Integer aid){
        Activity activity = activityService.selectByPrimaryKey(aid);
        return new ReturnMessage(200, activity);
    }
    @RequestMapping(value = "/activity/enterprise/{eid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据企业id提取活动", httpMethod = "GET")
    @ResponseBody
    public ReturnMessage getEnterpriseActivity(@ApiParam(required = true, name = "eid", value = "企业id") @PathVariable Integer eid){
        List<Activity> list = activityService.getEnterpriseActivity(eid);
        return new ReturnMessage(200, list);
    }
    @ApiOperation(value = "插入活动", notes = "插入活动到数据库")
    @ResponseBody
    @RequestMapping(value="/insertActivity", method = RequestMethod.POST)
    public ReturnMessage insertActivity( Activity activity,  @RequestParam(value = "coverfile", required = false) MultipartFile cofile,
                                        @RequestParam(value = "flyfile", required = false) MultipartFile flfile){
      String picture=manageFile(cofile);
  String video=manageFile(flfile);
      activity.setPicture(picture);
    activity.setVideo(video);
        int insertNum = activityService.insert(activity);
        return new ReturnMessage(200, insertNum);

    }


    public String manageFile(MultipartFile file) {
        //判断是否大于5M
        if(file.getSize()<5*1048576) {
            String key = LocalDateTime.now().getNano() + file.getOriginalFilename();
            String keys = "http://p8p5n2pli.bkt.clouddn.com/";
            Configuration cfg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = "f7S3xKlvKLRnqctORPPAth4GRw0JxtpqYUkgRhEL";
            String secretKey = "SDvAjl6hONBXxM5CcEC4uf5ffZ-tiBCJ5-bhI6Id";
            String bucket = "zhuifeng";
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file.getBytes(), key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                keys = keys + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return keys;
        }
        //大于5M返回空
        return null;
    }
}
