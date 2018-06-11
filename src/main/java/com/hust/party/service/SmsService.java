package com.hust.party.service;

/**
 * 短信服务的service<br/>
 * fan 2018/6/12 1:07
 */
public interface SmsService {
    /**
     *发送短信，uid为可选，多个参数用逗号隔开
     * @param templateid
     * @param param
     * @param mobile
     * @param uid
     * @return
     */
    String sendSms(String templateid, String param, String mobile, String uid);

    /**
     *批量发送短信，在mobile上多个手机号用英文逗号隔开
     * @param templateid
     * @param param
     * @param mobile
     * @param uid
     * @return
     */
    String sendSmsBatch(String templateid, String param, String mobile, String uid);

    /**
     *添加短信模板
     * @param type
     * @param template_name
     * @param autograph
     * @param content
     * @return
     */
    String addSmsTemplate(String type, String template_name, String autograph, String content);

    /**
     *获取短信模板
     * @param templateid
     * @param page_num
     * @param page_size
     * @return
     */
    String getSmsTemplate(String templateid, String page_num, String page_size);

    /**
     *编辑短信模板
     * @param templateid
     * @param type
     * @param template_name
     * @param autograph
     * @param content
     * @return
     */
    String editSmsTemplate(String templateid, String type, String template_name, String autograph, String content);

    /**
     *删除短信模板
     * @param templateid
     * @return
     */
    String deleterSmsTemplate(String templateid);

    /**
     * 返回拼接后的url，这个不是给外部调用的
     * @return
     */
    StringBuffer getStringBuffer();
}