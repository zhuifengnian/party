package com.hust.party.serviceimpl;

import com.hust.party.controller.CLibrary;
import com.hust.party.service.KuaidiSmsService;
import com.sun.jna.Native;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 歪果仁取快递<br/>
 * fan 2018/6/13 18:36
 */
@Service
public class KuaidiSmsServiceImpl implements KuaidiSmsService {

    @Override
    public String extractExpressCode(String msg) {
        //初始化
        CLibrary instance = (CLibrary)Native.loadLibrary(System.getProperty("user.dir")+"\\source\\NLPIR", CLibrary.class);
        int init_flag = instance.NLPIR_Init("", 1, "0");
        String resultString = null;
        if (0 == init_flag) {
            resultString = instance.NLPIR_GetLastErrorMsg();
            System.err.println("初始化失败！\n"+resultString);
            return null;
        }
        try {
            resultString = instance.NLPIR_ParagraphProcess(msg, 0);
            //对数据进行整理
            resultString = resultString.trim();
            //拿到拆分后的结果
            String[] split = resultString.split("\\s+");
            List<String> strs = new ArrayList<>();
            for(String s: split){
                boolean ret = judgeExtractCode(s);
                if(ret){
                    strs.add(s);
                }
            }
            if(strs.size() <= 0){
                return "没找到取货码";
            }
            return strs.get(0);
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            e.printStackTrace();
            return  null;
        }finally{
            instance.NLPIR_Exit();
        }
    }

    /**
     * 判断是否是取货码
     * @param s
     */
    private boolean judgeExtractCode(String s) {
        //判断是否是时间19:00
        Pattern timePattern = Pattern.compile("^(20|21|22|23|[0-1]\\d)(:|：)([0-5]\\d)$");
        if(timePattern.matcher(s).find()){
            return false;
        }
        //判断是否是电话
        if(s.length() >=11){
            return false;
        }

        //判断是否是别的情况
        return true;
    }
}