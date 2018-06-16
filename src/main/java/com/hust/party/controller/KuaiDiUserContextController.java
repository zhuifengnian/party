package com.hust.party.controller;

import com.hust.party.common.PinyinTool;
import com.hust.party.common.PinyinTool.Type;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.pojo.KuaiDiUserContext;
import com.hust.party.service.KuaiDiExpressService;
import com.hust.party.service.KuaidiSmsService;
import com.hust.party.service.KuaidiUserContextService;
import com.hust.party.util.KuaidiQiNiuUtil;
import com.hust.party.vo.KuaidiExpressVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class KuaiDiUserContextController
{
  @Autowired
  private KuaidiUserContextService kuaidiUserContextService;


    @RequestMapping(value = "/advice", method = RequestMethod.POST)
    @ApiOperation(value = "提意见接口")
    @ResponseBody
    public ReturnMessage insertKuaidiUserContext(KuaiDiUserContext kuaiDiUserContext) {


         int insert=kuaidiUserContextService.insert(kuaiDiUserContext);



        return new ReturnMessage(200, insert);
    }



    }

