package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;

import com.hust.party.common.PinyinTool;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Comment;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.service.ActivityService;
import com.hust.party.service.CommentService;
import com.hust.party.service.KuaiDiExpressService;
import com.hust.party.service.UserService;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.CommentVo;
import com.hust.party.vo.KuaidiExpressVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sourceforge.pinyin4j.PinyinHelper;
 import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
 import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
 import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.hust.party.common.PinyinTool.Type;
/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class KuaiDiExpressController
{
    @Autowired
    private KuaiDiExpressService kuaiDiExpressService;
    @RequestMapping(value = "/getAdress", method = RequestMethod.POST)
    @ApiOperation(value = "获取地址")
    @ResponseBody
    public ReturnMessage getAdress(String input) throws Exception{
        String msg = input;
        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(\\【[^\\]]*\\】)");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        KuaidiExpressVo kuaidiExpressVo =new KuaidiExpressVo();
       if(list.size()!=0){
        List<KuaiDiExpress>  list1= kuaiDiExpressService.getListKuaiinfo(list.get(0));
        KuaidiExpressVo kuaidiExpressVo1 =new KuaidiExpressVo();
           PinyinTool pinyinTool=new PinyinTool();
        if(list1.size()!=0) {
            for (int i = 0; i < list1.size(); i++) {
                if (msg.contains(list1.get(i).getKey1())) {
                    kuaidiExpressVo.setExpressStation(list1.get(0).getExpressStation());
                    kuaidiExpressVo.setPinyinexpressStation(pinyinTool.toPinYin(list1.get(i).getExpressStation()," ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setPinyinkey1(pinyinTool.toPinYin((list1.get(i).getKey1())," ",Type.FIRSTUPPER));
                    kuaidiExpressVo.setPinyinname(pinyinTool.toPinYin(list1.get(i).getName()," ",Type.FIRSTUPPER));
                    kuaidiExpressVo.setName(list1.get(i).getName());
                    kuaidiExpressVo.setExactCode("0");
                    kuaidiExpressVo.setLatitude(list1.get(i).getLatitude());
                    kuaidiExpressVo.setLongitude(list1.get(i).getLongitude());
                    kuaidiExpressVo.setKey1(list1.get(i).getKey1());
                    kuaidiExpressVo.setPicture(list1.get(i).getPicture());
                    break;

                }
            }
        }
       }
        return new ReturnMessage(200, kuaidiExpressVo);
    }

    @RequestMapping(value = "/insertKuaidiExpress", method = RequestMethod.POST)
    @ApiOperation(value = "存储信息")
    @ResponseBody
    public ReturnMessage insertKuaidiExpress(KuaiDiExpress kuaiDiExpress) {
         int insert=kuaiDiExpressService.insert(kuaiDiExpress);



        return new ReturnMessage(200, insert);
    }



 }

