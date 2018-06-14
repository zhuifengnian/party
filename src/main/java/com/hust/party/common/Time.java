package com.hust.party.common;


import com.hust.party.dao.ActivityMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import com.hust.party.service.EnterpriseService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component

public class Time  implements ServletContextListener {

    /**
     *
     */

    private final static Logger logger = Logger.getLogger(Time.class);
    private ActivityService activityService;
  private static Date date;

    //静态方法getInstance()使用默认时区和语言环境获得一个日历。
    Calendar calendar=Calendar.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Calendar date=Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        /**
         *  定制每天的24:00:00执行，若程序已超过24点启动,当天不再执行，等到明日二十四点再执行
         *  这样保证了时间一直是8点，而不会变成程序启动时间
         */
        calendar.set(year, month, day, 24, 00, 00);
        Date defaultdate = calendar.getTime();// 今天24点（默

        long daySpan=24*60*60*1000;
        java.util.Timer t=new java.util.Timer();
        t.schedule(new TimerTask() {

            public void run() {
                logger.info("更新开始");
                try {
                    moveStableMes();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        },defaultdate,daySpan);



    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("定时发送Xml信息监听--已关闭！");
    }
    @RequestMapping(value ="/move/moveStableMes", method = RequestMethod.GET)
     @ResponseBody
    public  void moveStableMes() throws SQLException  {
        this.activityService= BeanContext.getApplicationContext().getBean(ActivityService.class);

        long current = System.currentTimeMillis();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;

        Timestamp t1 = new Timestamp(twelve);
        List<Activity> list = activityService.getNowDay(t1);
        for (int i = 0; i < list.size(); i++) {
            Integer id=list.get(i).getId();
            activityService.updateNowDay(id);
        }
        System.out.println("定时器测试:"+System.currentTimeMillis());
    }

}
