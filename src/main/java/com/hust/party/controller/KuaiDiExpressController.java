package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;

import com.hust.party.common.PinyinTool;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.util.KuaidiQiNiuUtil;
import com.hust.party.util.QiNiuUtil;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luyue on 2018/5/12.
 */
@Controller
public class KuaiDiExpressController
{
    @Autowired
    private KuaiDiExpressService kuaiDiExpressService;
    @Autowired
    private KuaidiSmsService kuaidiSmsService;
    @Autowired
    private KuaidiSchoolService kuaidiSchoolService;
    @Autowired
    private KuaidiAdminService kuaidiAdminService;
//    @RequestMapping(value = "/getAdress", method = RequestMethod.POST)
//    @ApiOperation(value = "获取地址")
//    @ResponseBody
//    public ReturnMessage getAdress(String input) throws Exception{
//
//        KuaidiExpressVo kuaidiExpressVo1 =new KuaidiExpressVo();
//        KuaidiExpressVo kuaidiExpressVo =new KuaidiExpressVo();
//
//        boolean f=false;
//       String code= kuaidiSmsService.extractExpressCode(input);
//       //正则表达式判断括号中内容
//        List<String> list=new ArrayList<String>();
//        Pattern p = Pattern.compile("(\\【[^\\]]*\\】)");
//        Matcher m = p.matcher(input);
//        while(m.find()){
//            list.add(m.group().substring(1, m.group().length()-1));
//        }
//        if(list.size()==0){
//            kuaidiExpressVo1.setState(2);
//            return  new ReturnMessage(200, kuaidiExpressVo1);
//        }
//
////定义快递返回信息
//        List<KuaiDiExpress>  list1= kuaiDiExpressService.getListKuaiinfo(list.get(0));
//
//
//        if(code!=""){
//       if(list.size()!=0) {
//
//           PinyinTool pinyinTool = new PinyinTool();
//           if (list1.size() != 0) {
//               //当遇到符合快递点信息时，直接输出
//               for (int i = 0; i < list1.size(); i++) {
//                   if (input.contains(list1.get(i).getKey1())&&input.contains(list1.get(i).getName())) {
//                       kuaidiExpressVo.setExpressStation(list1.get(0).getExpressStation());
//                       kuaidiExpressVo.setExpressStation_E(pinyinTool.toPinYin(list1.get(i).getExpressStation(), " ", Type.FIRSTUPPER));
//                       kuaidiExpressVo.setLankmark_E(pinyinTool.toPinYin((list1.get(i).getKey1()), " ", Type.FIRSTUPPER));
//                       kuaidiExpressVo.setExpressCompant_E(pinyinTool.toPinYin(list1.get(i).getName(), " ", Type.FIRSTUPPER));
//                       kuaidiExpressVo.setExpressCompany(list1.get(i).getName());
//                       kuaidiExpressVo.setExtractCode(code);
//                       kuaidiExpressVo.setLatitude(list1.get(i).getLatitude());
//                       kuaidiExpressVo.setLongitude(list1.get(i).getLongitude());
//                       kuaidiExpressVo.setLandmark(list1.get(i).getKey1());
//                       kuaidiExpressVo.setPicture(list1.get(i).getPicture());
//                       kuaidiExpressVo.setState(1);
//                       f = true;
//                       break;
//
//                   }
//               }
//
//           }
//       }
//
//       }
//       if(f==false) {
//            kuaidiExpressVo1.setState(3);
//           return new ReturnMessage(200, kuaidiExpressVo1);
//       }
//        return new ReturnMessage(200, kuaidiExpressVo);
//    }

    @RequestMapping(value = "/insertKuaidiExpress", method = RequestMethod.POST)
    @ApiOperation(value = "存储信息")
    @ResponseBody
    public ReturnMessage insertKuaidiExpress(KuaiDiExpress kuaiDiExpress) {


        int insert=kuaiDiExpressService.insert(kuaiDiExpress);



        return new ReturnMessage(200, insert);
    }


    @RequestMapping(value = "/insertKuaidiExpressPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储图片信息")
    @ResponseBody
    public ReturnMessage insertKuaidiExpressExpressPicture(@RequestParam Integer id,@RequestParam(value = "flyfile", required = false) MultipartFile flfile,@RequestParam Integer num) {
        String picture="";
        KuaiDiExpress kuaiDiExpress =new KuaiDiExpress();
        kuaiDiExpress.setId(id);
        if(flfile!=null)
            picture  =KuaidiQiNiuUtil.manageFile(flfile);
        if(num == 1){
            kuaiDiExpress.setPicture(picture);
        }else if(num == 2){
            kuaiDiExpress.setPicture2(picture);
        }
        int insert=kuaiDiExpressService.updateByPrimaryKeySelective(kuaiDiExpress);


        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/getAdress", method = RequestMethod.POST)
    @ApiOperation(value = "提取快递地址")
    @ResponseBody
    public ReturnMessage getAdress(String input) throws Exception{

        KuaidiExpressVo kuaidiExpressVo1 =new KuaidiExpressVo();
        KuaidiExpressVo kuaidiExpressVo =new KuaidiExpressVo();

        boolean f=false;
        String code= kuaidiSmsService.extractExpressCode(input);



//定义快递返回信息
        List<KuaiDiExpress>  list1= kuaiDiExpressService.getListKuaiinfo1();


        PinyinTool pinyinTool = new PinyinTool();
        if (list1.size() != 0) {
            //当遇到符合快递点信息时，直接输出
            for (int i = 0; i < list1.size(); i++) {
                if ((input.contains(list1.get(i).getKey1())&&input.contains(list1.get(i).getName()))) {
                    kuaidiExpressVo.setExpressStation(list1.get(i).getExpressStation());

                    kuaidiExpressVo.setExpressStation_E(pinyinTool.toPinYin(list1.get(i).getExpressStation(), " ", Type.FIRSTUPPER));

                    kuaidiExpressVo.setPicture2(list1.get(i).getPicture2());
                    kuaidiExpressVo.setExpressCompant_E(pinyinTool.toPinYin(list1.get(i).getName(), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setExpressCompany(list1.get(i).getName());
                    kuaidiExpressVo.setExtractCode(code);
                    kuaidiExpressVo.setLatitude(list1.get(i).getLatitude());
                    kuaidiExpressVo.setLongitude(list1.get(i).getLongitude());
                    kuaidiExpressVo.setLandmark(list1.get(i).getKey1());
                    if(kuaidiExpressVo.getLandmark().equals("汉口银行")){
                        kuaidiExpressVo.setLankmark_E("Han Kou Yin Hang");
                    }
                    else
                        kuaidiExpressVo.setLankmark_E(pinyinTool.toPinYin((list1.get(i).getKey1()), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setPicture(list1.get(i).getPicture());
                    kuaidiExpressVo.setState(1);
                    f = true;
                    break;

                }
            }

        }


        if(f==false) {
            kuaidiExpressVo1.setState(2);
            return new ReturnMessage(200, kuaidiExpressVo1);
        }
        return new ReturnMessage(200, kuaidiExpressVo);
    }


    @RequestMapping(value = "/getAdresses", method = RequestMethod.POST)
    @ApiOperation(value = "提取快递地址")
    @ResponseBody
    public ReturnMessage getAdresses(String input,String school) throws Exception{
        Integer id=  kuaidiSchoolService.getSchoolId(school);
        KuaidiExpressVo kuaidiExpressVo1 =new KuaidiExpressVo();
        KuaidiExpressVo kuaidiExpressVo =new KuaidiExpressVo();

        boolean f=false;
        String code= kuaidiSmsService.extractExpressCode(input);



        //定义快递返回信息
        List<KuaiDiExpress>  list1= kuaiDiExpressService.getListKuaiinfo(id,null);


        PinyinTool pinyinTool = new PinyinTool();
        if (list1.size() != 0) {
            //当遇到符合快递点信息时，直接输出
            for (int i = 0; i < list1.size(); i++) {
                if ((input.contains(list1.get(i).getKey1())&&input.contains(list1.get(i).getName()))) {
                    kuaidiExpressVo.setExpressStation(list1.get(i).getExpressStation());

                    kuaidiExpressVo.setExpressStation_E(pinyinTool.toPinYin(list1.get(i).getExpressStation(), " ", Type.FIRSTUPPER));

                    kuaidiExpressVo.setPicture2(list1.get(i).getPicture2());
                    kuaidiExpressVo.setExpressCompant_E(pinyinTool.toPinYin(list1.get(i).getName(), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setExpressCompany(list1.get(i).getName());
                    kuaidiExpressVo.setExtractCode(code);
                    kuaidiExpressVo.setLatitude(list1.get(i).getLatitude());
                    kuaidiExpressVo.setLongitude(list1.get(i).getLongitude());
                    kuaidiExpressVo.setLandmark(list1.get(i).getKey1());
                    if(kuaidiExpressVo.getLandmark().equals("汉口银行")){
                        kuaidiExpressVo.setLankmark_E("Han Kou Yin Hang");
                    }
                    else
                        kuaidiExpressVo.setLankmark_E(pinyinTool.toPinYin((list1.get(i).getKey1()), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setPicture(list1.get(i).getPicture());
                    kuaidiExpressVo.setState(1);
                    f = true;
                    break;

                }
            }

        }


        if(f==false) {
            kuaidiExpressVo1.setState(2);
            return new ReturnMessage(200, kuaidiExpressVo1);
        }
        return new ReturnMessage(200, kuaidiExpressVo);
    }



    @RequestMapping(value = "/getSchoolAdress", method = RequestMethod.POST)
    @ApiOperation(value = "提取学校大使快递地址")
    @ResponseBody
    public ReturnMessage getSchoolAdress(Integer school_user_id,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber) throws Exception{
        PageInfo<KuaiDiExpress> pageinfo = new PageInfo<KuaiDiExpress>();
        pageSize=4;
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<KuaiDiAdmin> kuaidiAdmin=  kuaidiAdminService.selectByUid(school_user_id);
        List<KuaiDiExpress>  list1=  kuaiDiExpressService.getListKuaiinfo(kuaidiAdmin.get(0).getSchoolId(),page);
        pageinfo.setRows(list1);
        pageinfo.setTotal(kuaiDiExpressService.getListKuaiinfoCount(school_user_id));
        return new ReturnMessage(200, pageinfo);
    }

    @RequestMapping(value = "/updateSchoolAdress", method = RequestMethod.POST)
    @ApiOperation(value = "大使修改学校快递地址")
    @ResponseBody
    public ReturnMessage updateSchoolAdress(KuaiDiExpress kuaiDiExpress) throws Exception{
        int insert=0;
        if(kuaiDiExpress.getId()!=null) {
            insert=  kuaiDiExpressService.updateByPrimaryKeySelective(kuaiDiExpress);
        }

        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/deleteSchoolAdress", method = RequestMethod.POST)
    @ApiOperation(value = "大使删除学校快递地址")
    @ResponseBody
    public ReturnMessage deleteSchoolAdress(Integer id) throws Exception{
        int insert=0;
        if(id!=null) {
            insert=  kuaiDiExpressService.deleteByPrimaryKey(id);
        }

        return new ReturnMessage(200, insert);
    }


    @RequestMapping(value = "/getAdress1", method = RequestMethod.POST)
    @ApiOperation(value = "提取快递地址")
    @ResponseBody
    public ReturnMessage getAdress1(String input,Integer school_id) throws Exception{

        KuaidiExpressVo kuaidiExpressVo1 =new KuaidiExpressVo();
        KuaidiExpressVo kuaidiExpressVo =new KuaidiExpressVo();

        boolean f=false;
        String code= kuaidiSmsService.extractExpressCode(input);



        //定义快递返回信息
        List<KuaiDiExpress>  list1= kuaiDiExpressService.getListKuaiinfo(school_id,null);


        PinyinTool pinyinTool = new PinyinTool();
        if (list1.size() != 0) {
            //当遇到符合快递点信息时，直接输出
            for (int i = 0; i < list1.size(); i++) {
                if ((input.contains(list1.get(i).getKey1())&&input.contains(list1.get(i).getName()))) {
                    kuaidiExpressVo.setExpressStation(list1.get(i).getExpressStation());

                    kuaidiExpressVo.setExpressStation_E(pinyinTool.toPinYin(list1.get(i).getExpressStation(), " ", Type.FIRSTUPPER));

                    kuaidiExpressVo.setPicture2(list1.get(i).getPicture2());
                    kuaidiExpressVo.setExpressCompant_E(pinyinTool.toPinYin(list1.get(i).getName(), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setExpressCompany(list1.get(i).getName());
                    kuaidiExpressVo.setExtractCode(code);
                    kuaidiExpressVo.setLatitude(list1.get(i).getLatitude());
                    kuaidiExpressVo.setLongitude(list1.get(i).getLongitude());
                    kuaidiExpressVo.setLandmark(list1.get(i).getKey1());
                    if(kuaidiExpressVo.getLandmark().equals("汉口银行")){
                        kuaidiExpressVo.setLankmark_E("Han Kou Yin Hang");
                    }
                    else
                        kuaidiExpressVo.setLankmark_E(pinyinTool.toPinYin((list1.get(i).getKey1()), " ", Type.FIRSTUPPER));
                    kuaidiExpressVo.setPicture(list1.get(i).getPicture());
                    kuaidiExpressVo.setState(1);
                    f = true;
                    break;

                }
            }

        }


        if(f==false) {
            kuaidiExpressVo1.setState(2);
            return new ReturnMessage(200, kuaidiExpressVo1);
        }
        return new ReturnMessage(200, kuaidiExpressVo);
    }
}


