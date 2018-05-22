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
import com.hust.party.service.OrderUserService;
import com.hust.party.service.OrdersService;
import com.hust.party.util.ReflectUtil;
import com.hust.party.vo.AllOrderVo;
import com.hust.party.vo.EnterpriseOrderVo;
import com.hust.party.vo.PerenceActivityVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by luyue on 2018/5/12.
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
    @Autowired
    private OrderUserService orderUserService;
    @RequestMapping(value = "/enterprise/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getEnterpriseActivity(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        long current=System.currentTimeMillis();
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;
        List<Activity> lists=activityService.getAllCurrentActivity(eid);
        List <Activity> list=new ArrayList<>();
        Timestamp t = new Timestamp(zero);
        Date d = new Date(t.getTime());
        Timestamp t1 = new Timestamp(twelve);
        Date d1 = new Date(t1.getTime());
        for(int i=0;i<lists.size();i++){
            Activity activity=lists.get(i);
            Date date= activity.getActivityTime();
            if(date.getTime()>d.getTime()&&date.getTime()<d1.getTime()){
              list.add(activity);
            }

        }
        PageInfo<EnterpriseOrderVo> pageinfo=new PageInfo<EnterpriseOrderVo>();
        List<EnterpriseOrderVo> list2=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<list.size();i++){
            EnterpriseOrderVo enterpriseOrderVo = new EnterpriseOrderVo();
            Activity activity=list.get(i);
           List<Orders> list1=ordersService.getOrder(activity.getId());
           for(int j=0;j<list1.size();j++){
               enterpriseOrderVo.setId(list1.get(i).getId());

               enterpriseOrderVo.setActivityTime(activity.getActivityTime());
               enterpriseOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
               enterpriseOrderVo.setTitle(activity.getTitle());
             list2.add(enterpriseOrderVo);
           }
        }
        pageinfo.setRows(list2);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getAll/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取全部订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getActivity(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){

        List<Activity> lists=activityService.getAllCurrentActivity(eid);
        List<AllOrderVo> list2=new ArrayList<>();
        PageInfo<AllOrderVo> pageinfo=new PageInfo<AllOrderVo>();

        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<lists.size();i++){
            AllOrderVo allOrderVo =new AllOrderVo();
            Activity activity =lists.get(i);
            List<Orders> list1=ordersService.getOrders(activity.getId());
            for(int j=0;j<list1.size();j++){
                allOrderVo.setActivityTime(activity.getActivityTime());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                list2.add(allOrderVo);

            }
        }


        pageinfo.setRows(list2);
        pageinfo.setTotal(list2.size());
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNew/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取今日新接订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNewOrder(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        List<Activity> lists=activityService.getAllCurrentActivity(eid);

        PageInfo<EnterpriseOrderVo> pageinfo=new PageInfo<EnterpriseOrderVo>();
        List<EnterpriseOrderVo> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<lists.size();i++){
            Activity activity =lists.get(i);
          List<Orders> list1= ordersService.getNewOrder(activity.getId());
            long current=System.currentTimeMillis();
            long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
            long twelve=zero+24*60*60*1000-1;

            Timestamp t = new Timestamp(zero);
            Date d = new Date(t.getTime());
            Timestamp t1 = new Timestamp(twelve);
            Date d1 = new Date(t1.getTime());

            for(int j=0;j<list1.size();j++){
                Orders orders =list1.get(j);
                Date date= orders.getCreateTime();
                if(date.getTime()>d.getTime()&&date.getTime() <d1.getTime()){

                    EnterpriseOrderVo enterpriseOrderVo =new EnterpriseOrderVo();
                    enterpriseOrderVo.setTitle(activity.getTitle());
                    enterpriseOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                    enterpriseOrderVo.setActivityTime(activity.getActivityTime());
                    enterpriseOrderVo.setId(orders.getId());
                    list3.add(enterpriseOrderVo);
                }

            }

        }
         pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getNo/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取未消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getNoOrder(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        List<Activity> lists=activityService.getAllCurrentActivity(eid);

        PageInfo<AllOrderVo> pageinfo=new PageInfo<AllOrderVo>();
        List<AllOrderVo> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<lists.size();i++){
            Activity activity =lists.get(i);
            List<Orders> list1= ordersService.getNewOrder(activity.getId());

            for(int j=0;j<list1.size();j++){
                AllOrderVo allOrderVo = new AllOrderVo();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                 list3.add(allOrderVo);
            }

        }
        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getY/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已消费订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getYOrder(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        List<Activity> lists=activityService.getAllCurrentActivity(eid);

        PageInfo<AllOrderVo> pageinfo=new PageInfo<AllOrderVo>();
        List<AllOrderVo> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<lists.size();i++){
            Activity activity =lists.get(i);
            List<Orders> list1= ordersService.getYOrder(activity.getId());

            for(int j=0;j<list1.size();j++){
                AllOrderVo allOrderVo = new AllOrderVo();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                list3.add(allOrderVo);
            }

        }
        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise/getQ/{eid}", method = RequestMethod.POST)
    @ApiOperation(value = "根据企业id提取已取消订单", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getQOrder(@ApiParam(required = true, name = "eid", value = "活动id") @PathVariable Integer eid,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
        List<Activity> lists=activityService.getAllCurrentActivity(eid);

        PageInfo<AllOrderVo> pageinfo=new PageInfo<AllOrderVo>();
        List<AllOrderVo> list3=new ArrayList<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        for(int i=0;i<lists.size();i++){
            Activity activity =lists.get(i);
            List<Orders> list1= ordersService.getQOrder(activity.getId());

            for(int j=0;j<list1.size();j++){
                AllOrderVo allOrderVo = new AllOrderVo();
                allOrderVo.setPreferentialPrice(activity.getPreferentialPrice());
                allOrderVo.setState(list1.get(j).getState());
                allOrderVo.setTitle(activity.getTitle());
                allOrderVo.setMinuPeople(activity.getMinuPeople());
                allOrderVo.setId(list1.get(j).getId());
                allOrderVo.setCreateTime(list1.get(j).getCreateTime());
                allOrderVo.setActivityTime(activity.getActivityTime());
                list3.add(allOrderVo);
            }

        }
        pageinfo.setRows(list3);
        return new ReturnMessage(200, pageinfo);
    }
    @RequestMapping(value = "/enterprise", method = RequestMethod.POST)
    @ApiOperation(value = "企业注册接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterprise(Enterprise enterprise,@RequestParam(value = "flyfile", required = false) MultipartFile flfile){
        Integer uid = enterpriseService.selectUserByChatId(enterprise.getOpenId());
        int insert=0;
        if(uid==null) {
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
        enterprise.setLicence(license);
        int insert=  enterpriseService.updateByPrimaryKey(enterprise);
        return new ReturnMessage(200, insert);
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
