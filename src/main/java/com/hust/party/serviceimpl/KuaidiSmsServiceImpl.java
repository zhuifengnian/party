package com.hust.party.serviceimpl;

import com.hust.party.service.KuaidiSmsService;
import org.springframework.stereotype.Service;

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
        Pattern comaPattern = Pattern.compile("[{!\"#$%&'()+,./:;<=>?@【 】^_`|}~，。（）：；〖 〗『 』 @ ]");
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

        //遍历潜在的取货码，并作出筛选
        String tmpRet = "";     //用于得到处理时的结果
        for(int i=0;i<splited.length;i++){
            //出现包含星号的直接过滤
            if(splited[i].contains("*")){
                continue;
            }
            //拿到1-1-5325这样的取货码并认为它就是所要的结果，直接退出循环
            if(splited[i].length()==8&&splited[i].contains("-")){
                tmpRet = splited[i];
                break;
            }
            //长度小于8时，拿到最长的作为取货码，并且同等长度取后者
            if((splited[i].length()<8)){
                if(tmpRet.length()<=splited[i].length()) {
                    tmpRet=splited[i];
                }
            }
        }

        if(tmpRet.length()==8&&tmpRet.contains("-"))
            tmpRet=tmpRet;
        else if(tmpRet.length()>7)
            tmpRet="";
        ret = tmpRet;
        return ret;
    }

}