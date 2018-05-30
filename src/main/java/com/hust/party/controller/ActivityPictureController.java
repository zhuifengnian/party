package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.ActivityPicture;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.*;
import com.hust.party.util.QiNiuUtil;
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
            picture=QiNiuUtil.manageFile(flfile);
        activitypicture.setPictureUrl(picture);
        int insertNum = activitypictureService.insert(activitypicture);
        return new ReturnMessage(200, insertNum);

    }

}
