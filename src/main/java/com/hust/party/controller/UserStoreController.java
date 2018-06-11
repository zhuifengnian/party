package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.PageInfo;
import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.User;
import com.hust.party.pojo.UserForce;
import com.hust.party.pojo.UserStore;
import com.hust.party.service.UserForceService;
import com.hust.party.service.UserService;
import com.hust.party.service.UserStoreService;
import com.hust.party.vo.PerenceActivityVO;
import com.hust.party.vo.UserStoreEnterpriseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/6/5
 */
@Controller
public class UserStoreController
{
    @Autowired
    private UserStoreService userStoreService;
    @Autowired
    private UserService userService;
    @ApiOperation(value = "插入收藏", notes = "插入收藏到数据库")
    @ResponseBody
    @RequestMapping(value="/insertUserStore", method = RequestMethod.POST)
    public ReturnMessage insertUserStore( UserStore userStore){
        String text="用户不存在";
        List<UserStore> list= userStoreService.judgeStore(userStore.getUserId(),userStore.getEnterpriseId());
        int insert=list.size();
        int state=0;
       User user=  userService.selectByPrimaryKey(userStore.getUserId());
       if(user!=null) {
           if (insert != 0)
               state = list.get(0).getState();
           if (insert == 0) {
               userStore.setState(1);
               userStore.setCreateTime(new Date());
               userStore.setUpdateTime(new Date());
               int inserts = userStoreService.insert(userStore);
               if (inserts != 0)
                   text = "收藏成功";
               else
                   text = "未插入成功";
           } else if (insert != 0 && state == 1) {
               userStore.setId(list.get(0).getId());
               userStore.setState(2);
               userStore.setUpdateTime(new Date());
               int inserts = userStoreService.updateByPrimaryKeySelective(userStore);
               if (inserts != 0)
                   text = "删除成功";
               else
                   text = "未删除成功";

           } else if (insert != 0 && state == 2) {
               userStore.setId(list.get(0).getId());
               userStore.setState(1);
               userStore.setUpdateTime(new Date());
               userStoreService.updateByPrimaryKeySelective(userStore);

               text = "再次收藏成功";
           }
       }

        return new ReturnMessage(200, text);

    }

    @ApiOperation(value = "提取收藏", notes = "提取收藏到数据库")
    @ResponseBody
    @RequestMapping(value="/gettUserStore", method = RequestMethod.POST)
    public ReturnMessage getUserStore( @RequestParam("user_id") Integer userId,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
    PageInfo<UserStoreEnterpriseVO> pageinfo = new PageInfo<UserStoreEnterpriseVO>();
    pageSize=4;
    pageinfo.setPageNum(pageNumber);
    pageinfo.setPageSize(pageSize);
    Page page = new Page();
    page.setPageNumber(pageNumber);
    page.setPageSize(pageSize);

         pageinfo.setRows( userStoreService.getUserStore(userId,page));
         pageinfo.setTotal(userStoreService.getUserStorecount(userId));
        return new ReturnMessage(200, pageinfo);

    }
    @ApiOperation(value = "取消收藏", notes = "提取收藏到数据库")
    @ResponseBody
    @RequestMapping(value="/deletetUserStore", method = RequestMethod.POST)
    public ReturnMessage deleteUserStore( @RequestParam("userStore_id") Integer id){
        int insert=0;
        if(id!=null) {
            UserStore userStore = new UserStore();
            userStore.setId(id);
            userStore.setState(2);
            userStore.setUpdateTime(new Date());

             insert = userStoreService.updateByPrimaryKeySelective(userStore);
        }
        return new ReturnMessage(200, insert);

    }
}
