package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.ActivityPicture;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.*;
import com.hust.party.util.ReflectUtil;
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
public class ActivityPictureController
{
  @Autowired
  private ActivityPictureService activitypictureService;
    @ApiOperation(value = "插入相册", notes = "插入相册到数据库")
    @ResponseBody
    @RequestMapping(value="/insertActivityPicture", method = RequestMethod.POST)
    public ReturnMessage insertActivityPicture( ActivityPicture activitypicture,
                                         @RequestParam(value = "flyfile", required = false) MultipartFile flfile){
        String picture=null;
        if(flfile!=null)
            picture=manageFile(flfile);
        activitypicture.setPictureUrl(picture);
        int insertNum = activitypictureService.insert(activitypicture);
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
