package com.hust.party.controller;

import com.hust.party.common.BaseController;
import com.hust.party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hust.party.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wei on 2017/7/14.
 */
@Controller
@RequestMapping("/user")
public class UserCenterController extends BaseController{
    @Autowired
    UserService userService;
	@RequestMapping("/center")
	public ModelAndView userCenter(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		User user = (User) req.getSession().getAttribute("user");
        HashMap<String, Object> map = userService.getuserdetail(user.getId());
        mav.addObject("map", map);
		mav.setViewName("userCenter");
		return mav;
	}
	@RequestMapping("/updatePassword")
    public String update(HttpServletRequest req, String password) {
	    User user = (User) req.getSession().getAttribute("user");
	    userService.changepassword(user.getId(), password);
	    return "redirect:/login.jsp";
    }
    @RequestMapping("/resetPassword")
    public String reset(HttpServletRequest req) {
	    User user = (User) req.getSession().getAttribute("user");
        userService.resetpassword(user.getId());
	    return "redirect:/login.jsp";
    }
}
