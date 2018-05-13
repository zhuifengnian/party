package com.hust.party.test.servicetest;

import com.hust.party.dao.ActivityMapper;
import com.hust.party.dao.ActivityMapper2;
import com.hust.party.pojo.Activity;
import com.hust.party.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 专门用来做单元测试<br/>
 * fan 2018/4/26 23:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml","classpath:springmvc/spring-mvc.xml"})
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
public class ServiceTest {
    @Autowired
    private ActivityMapper2 activityMapper;

    @Test
    public void testActivity(){
        Activity activity = activityMapper.getActivity(1);
        System.out.println(activity);
    }
}