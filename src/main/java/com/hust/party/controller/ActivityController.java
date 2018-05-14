package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiExpection;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class ActivityController
{
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activity/{aid}", method = RequestMethod.GET)
    @ApiOperation(value = "教程", httpMethod = "GET", notes = "教程")
    @ResponseBody
    public ReturnMessage getActivity(@ApiParam(required = true, name = "aid", value = "活动id") @PathVariable Integer aid){
        Activity activity = activityService.selectByPrimaryKey(aid);
        return new ReturnMessage(200, activity);
    }
}
