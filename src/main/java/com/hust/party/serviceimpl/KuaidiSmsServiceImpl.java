package com.hust.party.serviceimpl;

import com.hust.party.controller.CLibrary;
import com.hust.party.service.KuaidiSmsService;
import com.sun.jna.Native;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 歪果仁取快递<br/>
 * fan 2018/6/13 18:36
 */
@Service
public class KuaidiSmsServiceImpl implements KuaidiSmsService {

    @Override
    public String extractExpressCode(String msg) {
        String ret = new String(msg);
        Matcher timerMatcher=null;

        //判断是否是时间19:00这种
        Pattern timePattern = Pattern.compile("(20|21|22|23|[0-1]\\d)(:|：)([0-5]\\d)");
      timerMatcher = timePattern.matcher(ret);
       ret=timerMatcher.replaceAll("");

        //删除标点符号s
        Pattern comaPattern = Pattern.compile("[{!\"#$%&'()*+,./:;<=>?@【】^_`|}~，。（）：；]");
        timerMatcher = comaPattern.matcher(ret);
        ret=timerMatcher.replaceAll("");

        //删除所有汉字
        String regex ="[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(ret);
        ret = mat.replaceAll(" ");

        //删除首尾空格
        ret=ret.trim();

        String[] splited = ret.split("\\s+");

        //设定规则，大于2和小于8满足我们需求，如果同时有多个满足，取大的作为取货码
        for(int i=0;i<splited.length;i++){
            if(splited[i].length()<11){
                if(ret.length()>splited[i].length()&&i!=0) {
                    ret=ret;
                }
                else
                    ret=splited[i];
            }

        }

        return ret;
    }

}