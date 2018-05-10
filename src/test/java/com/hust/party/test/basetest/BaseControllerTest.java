package com.hust.party.test.basetest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Magic_Yang on 17/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
@ContextConfiguration(locations = {"classpath:application.xml","classpath:springmvc/spring-mvc.xml"})
public class BaseControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

}
