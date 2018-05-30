package com.hust.party.controller;

import com.google.gson.Gson;
import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.pojo.Orders;
import com.hust.party.service.ActivityService;
import com.hust.party.service.EnterpriseService;
import com.hust.party.service.OrdersService;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.AllOrderVO;
import com.hust.party.vo.EnterpriseActivityVo;
import com.hust.party.vo.EnterpriseOrderVo;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by luyue on 2018/5/23
 */
@Controller
public class EnterpriseController
{
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private OrdersService ordersService;
    @RequestMapping(value = "/enterprise/activity", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getEnterpriseActivity(@RequestParam("eid") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){


        PageInfo<EnterpriseActivityVo> pageinfo=new PageInfo<EnterpriseActivityVo>();
        List<EnterpriseActivityVo> list1= new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        Activity activity =new Activity();
        activity.setEnterpriseId(eid);
      List <Activity> list=  activityService.select(activity,page);
      for(int i=0;i<list.size();i++){
          EnterpriseActivityVo enterpriseActivityVo =new EnterpriseActivityVo();
          ReflectUtil.copyProperties(enterpriseActivityVo, list.get(i));
          Orders orders =new Orders();
          orders.setActivityId(list.get(0).getId());
          orders.setState(2);
          enterpriseActivityVo.setSum(ordersService.selectCount(orders));
list1.add(enterpriseActivityVo);
      }
        pageinfo.setRows(list1 );
        int count=activityService.selectCount(activity);
        pageinfo.setTotal(count);
        //  List<Activity> list = activityService.getEnterpriseActivity(eid);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNowEnterpriseActivity", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNowEnterpriseActivity(@RequestParam("eid")@PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
       if(pageSize==null)
           pageSize=10;
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
        PageInfo<EnterpriseOrderVo> pageinfo=new PageInfo<EnterpriseOrderVo>();
        List<EnterpriseOrderVo> list2=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        Timestamp t = new Timestamp(zero);
        Date d = new Date(t.getTime());
        Timestamp t1 = new Timestamp(twelve);
        Date d1 = new Date(t1.getTime());

            Orders orders =new Orders();

        Map map= com.hust.party.common.ReflectUtil.generalMap(orders,page);
        map.put("eid",eid);
        map.put("d",t);
        map.put("t",t1);
        map.put("state",2);
           List<Orders> list1=ordersService.getActivityOrder(map);
            pageinfo.setTotal(ordersService.getActivityCount(map));
           for(int j=0;j<list1.size();j++){

               Orders order1 =list1.get(j);
               Activity activity= activityService.selectByPrimaryKey(order1.getActivityId());
               EnterpriseOrderVo enterpriseOrderVo = new EnterpriseOrderVo();
               enterpriseOrderVo.setId(list1.get(j).getId());

               enterpriseOrderVo.setActivityTime(activity.getActivityTime());
               enterpriseOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
               enterpriseOrderVo.setTitle(activity.getTitle());
             list2.add(enterpriseOrderVo);
           }

        pageinfo.setRows(list2);

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getAll", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取全部订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getAllActivity(@RequestParam("eid") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
       if(pageSize==null)
           pageSize=10;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<AllOrderVO> list2=new ArrayList<>();

            Orders orders =new Orders();
           orders.setEnterpriseId(eid);
            List<Orders> list1=ordersService.select(orders,page);
            pageinfo.setTotal(ordersService.selectCount(orders));

            for(int j=0;j<list1.size();j++){
                Orders order1=list1.get(j);
                Activity activity =activityService.selectByPrimaryKey(order1.getActivityId());
                AllOrderVO allOrderVo =new AllOrderVO();
                allOrderVo.setActivityTime(activity.getActivityTime());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                list2.add(allOrderVo);

        }

        pageinfo.setRows(list2);

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNew", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日新接订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNewOrder(@RequestParam("eid") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=10;
        PageInfo<EnterpriseOrderVo> pageinfo=new PageInfo<EnterpriseOrderVo>();
        List<EnterpriseOrderVo> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;

        Timestamp t = new Timestamp(zero);
        Timestamp t1 = new Timestamp(twelve);
        Orders orders =new Orders();
        Map map= com.hust.party.common.ReflectUtil.generalMap(orders,page);
        map.put("eid",eid);
        map.put("d",t);
        map.put("t",t1);
        map.put("state",2);
        List<Orders> list1=ordersService.getOrders(map);
        pageinfo.setTotal(ordersService.getCount(map));
            for(int j=0;j<list1.size();j++){
                Orders order1 =list1.get(j);
               Activity activity= activityService.selectByPrimaryKey(order1.getActivityId());
                    EnterpriseOrderVo enterpriseOrderVo =new EnterpriseOrderVo();
                    enterpriseOrderVo.setTitle(activity.getTitle());
                    enterpriseOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                    enterpriseOrderVo.setActivityTime(activity.getActivityTime());
                    enterpriseOrderVo.setId(order1.getId());
                    list3.add(enterpriseOrderVo);

            }
         pageinfo.setRows(list3);

        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取未消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNoOrder(@RequestParam("eid") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=10;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        List<AllOrderVO> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

            Orders orders =new Orders();
            orders.setState(2);
            orders.setEnterpriseId(eid);
            List<Orders> list1= ordersService.select(orders,page);
            pageinfo.setTotal(ordersService.selectCount(orders));
            for(int j=0;j<list1.size();j++){
                Orders order1=list1.get(j);
                Activity activity =activityService.selectByPrimaryKey(order1.getActivityId());
                AllOrderVO allOrderVo = new AllOrderVO();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                 list3.add(allOrderVo);
            }


        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getY", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getYOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=10;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        List<AllOrderVO> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

            Orders orders =new Orders();
            orders.setEnterpriseId(eid);
            orders.setState(5);
            List<Orders> list1= ordersService.select(orders,page);
        pageinfo.setTotal(ordersService.selectCount(orders));
            for(int j=0;j<list1.size();j++){
                Orders order1=list1.get(j);
                Activity activity =activityService.selectByPrimaryKey(order1.getActivityId());
                AllOrderVO allOrderVo = new AllOrderVO();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                list3.add(allOrderVo);


        }
        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getQ", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已取消订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getQOrder(@RequestParam("eid") Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        if(pageSize==null)
            pageSize=10;
        PageInfo<AllOrderVO> pageinfo=new PageInfo<AllOrderVO>();
        List<AllOrderVO> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);


            Orders orders =new Orders();
            orders.setEnterpriseId(eid);
            orders.setState(0);
            List<Orders> list1= ordersService.select(orders,page);
        pageinfo.setTotal(ordersService.selectCount(orders));
            for(int j=0;j<list1.size();j++){
                Orders order1=list1.get(j);
                Activity activity =activityService.selectByPrimaryKey(order1.getActivityId());
                AllOrderVO allOrderVo = new AllOrderVO();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                list3.add(allOrderVo);
            }


        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise", method = RequestMethod.POST)
    @ApiOperation(value = "企业注册接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterprise(Enterprise enterprise,@RequestParam(value = "flyfile", required = false) MultipartFile flfile){
        Integer eid = enterpriseService.selectUserByChatId(enterprise.getOpenId());
        int insert=0;
        if(eid==null) {
            String license = null;
            if (flfile != null)
                license = manageFile(flfile);
            enterprise.setLicence(license);
           insert = enterpriseService.insert(enterprise);
        }
        else
            insert=0;
        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/updateEnterprise", method = RequestMethod.POST)
    @ApiOperation(value = "修改企业信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateEnterprise(Enterprise enterprise,@RequestParam(value = "flyfile", required = false) MultipartFile flfile){
        String license=null;
        if(flfile!=null)
            license=manageFile(flfile);
        ;
        enterprise.setLicence(license);
        int insert=  enterpriseService.updateByPrimaryKey(enterprise);
        return new ReturnMessage(200, insert);
    }
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ApiOperation(value = "企业撤销自己的活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage deleteActivity(@RequestParam("aid") Integer aid){
      String text="";
      Activity activity =new Activity() ;
      activity.setState(2);
      activity.setId(aid);

      Activity activity1=activityService.selectByPrimaryKey(aid);
      if(activity1.getArriveCopies()!=0)
         text="活动卖出，暂时无法取消,可以进行修改";
      else {

              activity.setState(2);
          int insert = activityService.updateByPrimaryKey(activity);
          if (insert != aid)
               text="撤销未成功，请重新撤销";
          else
               text="撤销完成";
      }
        return new ReturnMessage(200, text);
    }
    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    @ApiOperation(value = "企业修改自己的活动", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage updateActivity(  Activity activity){

        String text="";

            Activity activity1 = activityService.selectByPrimaryKey(activity.getId());
            if (activity1.getState() == 1) {
                if (activity.getCopies() != null) {
                    if (activity.getCopies() < activity1.getArriveCopies())
                        text = "暂时不能修改，总份数小于已拼团份数";
                    else {
                        int insert = activityService.updateByPrimaryKey(activity);
                        if (insert != activity.getId())
                            text = "修改未成功，请重新修改";
                        else
                            text = "修改完成";
                    }
                }
                int insert = activityService.updateByPrimaryKey(activity);
                if (insert != activity.getId())
                    text = "修改未成功，请重新修改";
                else
                    text = "修改完成";
            } else
                text = "已撤销活动，不可以修改";


        return new ReturnMessage(200, text);
    }
    public String manageFile(MultipartFile file) {
        //判断是否大于5M
        if(file.getSize()<5*1048576) {
            String key = LocalDateTime.now().getNano() + file.getOriginalFilename();
            String keys = "http://p8p5n2pli.bkt.clouddn.com/";
            Configuration cfg = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = "f7S3xKlvKLRnqctORPPAth4GRw0JxtpqYUkgRhEL";
            String secretKey = "SDvAjl6hONBXxM5CcEC4uf5ffZ-tiBCJ5-bhI6Id";
            String bucket = "zhuifeng";
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file.getBytes(), key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                keys = keys + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return keys;
        }
        //大于5M返回空
        return null;
    }
}
