package com.hust.party.test.servicetest;

import com.hust.party.dao.ActivityMapper;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Carousel;
import com.hust.party.pojo.Order;
import com.hust.party.pojo.UserRole;
import com.hust.party.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

/**
 * 专门用来做单元测试<br/>
 * fan 2018/4/26 23:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml","classpath:springmvc/spring-mvc.xml"})
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
public class ServiceTest {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderUserService orderUserService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarouselService carouselService;

    @Test
    public void testActivity(){
        Activity activity = activityService.selectByPrimaryKey(1);
        System.out.println(activity);
    }

    @Test
    public void testInsertActivity(){
        Activity activity = new Activity();
        activity.setAddress("湖北省某某市");
        activity.setContainPeople(3);
        activity.setFeature("好玩");
        activity.setOriginalPrice(new BigDecimal(203.5));
        activity.setEnterpriseId(1);
        activity.setTitle("三国杀");
        activityService.insert(activity);
    }

    @Test
    public void testGetUserId(){
        Integer qwert = userService.selectUserByChatId("qwert");
        System.out.println(qwert);
    }

    @Test
    public void testOrderUser(){
        List<Integer> integers = orderUserService.selectOrdersByUid(1);

        System.out.println(integers);
    }

    @Test
    public void testSelectUserCount(){
        Integer integer = orderUserService.selectUserCnt(2);
        System.out.println(integer);
    }
    @Test
    public void testUserService(){
        Integer qwert = userService.selectUserByChatId("qwert");
        System.out.println(qwert);
    }
    @Test
    public void testOrderService(){
        Order order = orderService.selectByPrimaryKey(3);
        System.out.println(order);
    }
    
    @Test
    public void testCarousel(){
        int i = carouselService.selectCount(null);
        System.out.println(i);

        List<Carousel> select = carouselService.select(null, null);
        System.out.println(select);
    }
}