package com.hust.party.serviceimpl;

import com.hust.party.service.SmsService;
import com.hust.party.smsnotify.HttpClientUtil;
import com.hust.party.util.PropertieUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 短信服务service实现类<br/>
 * fan 2018/6/12 1:08
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Value("${sms.rest_server}")
    private String server;

    @Value("${sms.sid}")
    private String sid;

    @Value("${sms.token}")
    private String token;

    @Value("${sms.appid}")
    private String appid;

    @Override
    public String sendSms(String templateid, String param, String mobile,
                          String uid) {

        String result = "";

        try {
            String url = getStringBuffer().append("/sendsms").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
            jsonObject.put("param", param);
            jsonObject.put("mobile", mobile);
            jsonObject.put("uid", uid);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String sendSmsBatch(String templateid, String param, String mobile,
                               String uid) {

        String result = "";

        try {
            String url = getStringBuffer().append("/sendsms_batch").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
            jsonObject.put("param", param);
            jsonObject.put("mobile", mobile);
            jsonObject.put("uid", uid);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String addSmsTemplate(String type, String template_name,
                                 String autograph, String content) {

        String result = "";

        try {
            String url = getStringBuffer().append("/addsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("type", type);
            jsonObject.put("template_name", template_name);
            jsonObject.put("autograph", autograph);
            jsonObject.put("content", content);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getSmsTemplate(String templateid, String page_num,
                                 String page_size) {

        String result = "";

        try {
            String url = getStringBuffer().append("/getsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
            jsonObject.put("page_num", page_num);
            jsonObject.put("page_size", page_size);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String editSmsTemplate(String templateid, String type,
                                  String template_name, String autograph, String content) {

        String result = "";

        try {
            String url = getStringBuffer().append("/editsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
            jsonObject.put("type", type);
            jsonObject.put("template_name", template_name);
            jsonObject.put("autograph", autograph);
            jsonObject.put("content", content);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String deleterSmsTemplate(String templateid) {

        String result = "";

        try {
            String url = getStringBuffer().append("/deletesmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);

            String body = jsonObject.toString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(server).append("/ol/sms");
        return sb;
    }

}