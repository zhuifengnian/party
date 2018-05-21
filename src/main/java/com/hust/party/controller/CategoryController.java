package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Category;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.*;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.CategoryActivityVO;
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
public class CategoryController
{
@Autowired
private CategoryService categoryService;
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ApiOperation(value = "获取类别", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getActivity(@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        Category category =new Category();
        category.setName("ktv");
        PageInfo<Category> pageinfo=new PageInfo<Category>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows(categoryService.select(category,page));
        pageinfo.setTotal(categoryService.selectCount(category));
        return new ReturnMessage(200, pageinfo);
    }

    /**
     * 获取指定类别下的所有活动
     */
    @RequestMapping(value="/category/{cid}")
    @ApiOperation(value="获取该类别下所有的活动", httpMethod="GET")
    @ResponseBody
    public ReturnMessage getActivitiesByCategory(@ApiParam(value = "类别id", required = true)@PathVariable("cid") Integer cid,
           @ApiParam(value = "当前页数", required = false, defaultValue = "1")@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
           @ApiParam(value = "每页个数", required = false, defaultValue = "10")@RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageInfo<CategoryActivityVO> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNumber);
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        List<CategoryActivityVO> activities = categoryService.selectActivityByCategory(cid, page);
        pageInfo.setRows(activities);
        pageInfo.setTotal(categoryService.getActivityByCidCnt(cid));
        return new ReturnMessage(200, pageInfo);
    }

}
