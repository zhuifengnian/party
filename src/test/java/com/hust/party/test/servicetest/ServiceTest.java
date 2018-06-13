package com.hust.party.test.servicetest;

import com.hust.party.common.*;
import com.hust.party.dao.ActivityMapper;
import com.hust.party.pojo.*;
import com.hust.party.service.*;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.OrderActivityVO;
import com.hust.party.vo.PerenceActivityVO;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

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
    private OrdersService ordersService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private UserForceService userForceService;



@Test
public void testGetAcitivity(){
   // Integer list = activityService.getNameActivityCount("轰趴馆");
 // System.out.println("+++++++++++++++++"+list);
   // List<PerenceActivityVO> list =activityService.getNameActivity("轰趴馆",null);
   // System.out.println("+++++++++++++++++"+list);
 //  List<AllOrderVO> list =enterpriseService.getYOrder(1,null);
   // System.out.println("+++++++++++++++++"+list);
   // userForceService.insertcolonelForce(25);
//   List<EnterpriseActivityVo> list= enterpriseService.getAllActivity(1,null);
   // long current=System.currentTimeMillis();
    //long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    //long twelve=zero+24*60*60*1000-1;

 //   Timestamp t1 = new Timestamp(twelve);
 //   List<Activity> list =activityService.getNowDay(t1);
    Date activity_time=new Date();
    Date arrive_time=new Date();

   // activity_time=dateFormat.DateFormats(activity_time.getTime());
  // int i= activityMapper.updateDay(3);

  //  System.out.println("+++++++++++++++++"+i);

}
    @Test
    public void testGetActivity() throws  Exception{
      //  Activity activity = activityService.selectByPrimaryKey(14);
       // System.out.println(activity);

//       List<Integer> list= new ArrayList<>();
//       list.add(25);
//    int insert=  userForceService.insertForce(list);
//        System.out.println(insert);
       // String msg = "【好递讯美】你好，你的快递到了请到新博士生公寓旁老附中邮政服务中心来取，谢谢取件码R303";
       //System.out.print( "++++++++"+msg.contains("新博士生公寓旁老附中"));
       // PinyinTool tool = new PinyinTool();
        //System.out.println("刘亚壮的运行测试结果为====" + tool.toPinYin("刘亚壮", "", PinyinTool.Type.LOWERCASE));
    }
//@Test
//public void getCategory(){
//
//    PageInfo<Orders> pageinfo=new PageInfo<Orders>();
//    pageinfo.setPageNum(2);
//    pageinfo.setPageSize(10);
//    Page page= new Page();
//    page.setPageNumber(2);
//    page.setPageSize(10);
//    long current=System.currentTimeMillis();
//    long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
//    long twelve=zero+24*60*60*1000-1;
//
//    Timestamp t = new Timestamp(zero);
//    Date d = new Date(t.getTime());
//    Timestamp t1 = new Timestamp(twelve);
//    Date d1 = new Date(t1.getTime());
//    Orders orders =new Orders();
//    Map map= ReflectUtil.generalMap(orders,page);
//    map.put("eid",1);
//    map.put("d",t);
//    map.put("t",t1);
//
////pageinfo.setRows(ordersService.getOrders(map));
//
//System.out.println("分页信息：" + pageinfo);
//}
    @Test
    public void testActivity(){
        List<PerenceActivityVO> activity = activityService.getAllActivity(null);
        System.out.println("++++++++++++++++++++"+activity);
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
      //  List<Integer> integers = orderUserService.selectOrdersByUid(1);

      //  System.out.println(integers);
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
    public void testCarousel(){
        int i = carouselService.selectCount(null);
        System.out.println(i);

        List<Carousel> select = carouselService.select(null, null);
        System.out.println(select);
    }

    @Test
    public void testlistAllOrders(){
        List<Integer> integers = orderUserService.selectOrdersByUid(1, new Page());
        System.out.println(integers);
    }
    @Test
    public void testListOrders(){
        List<OrderActivityVO> orderActivityVOS = orderUserService.selectOrders(14, Const.ORDER_LIST_STATUS_DRAWBACKING,null);
        System.out.println(orderActivityVOS);
    }
    @Test
    public void selectUserId(){
       int insert=   userService.selectUserByChatId("oReIb5LdIAyYRRWD76b8wic9q2NY");
       System.out.print("+++++++++++++++++++++++++"+insert);
    }
}