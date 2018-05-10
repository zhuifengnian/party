package com.hust.party.controller;

import com.hust.party.common.BaseController;
import com.hust.party.pojo.User;
import com.hust.party.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Magic_Yang on 17/7/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    @Resource
    public LoginService lgservice;

    @RequestMapping("Logincheck")
    public void logincheck(@RequestParam("name") String username,
                             @RequestParam("password") String password,
							 RedirectAttributes attr, Model model,
                             HttpServletResponse response) throws IOException{
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        User user = new User();
        user.setId(username);
		request.getSession().setAttribute("username",username);
        user.setPassword(password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch (UnknownAccountException e) {
            System.out.println("noAccount");
            response.getWriter().write("noAccount");
            return;
        } catch (IncorrectCredentialsException e) {
            System.out.println("wrongPassword");
            response.getWriter().write("wrongPassword");
            return;
        }
        System.out.println("对了！！");
        Map map = lgservice.queryuser(user);
        User usr = (User) map.get("user");
        request.getSession().setAttribute("user",user);
        attr.addFlashAttribute("user", usr);
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.hasRole("stu")){
//            response.getWriter().write("/bookSharing/stuBookSharing");
//        }
//        else if(subject.hasRole("tea")){ response.getWriter().write("/teaBookSharing/show?pageNumber=1&pageSize=10");}
//        else{
//            response.getWriter().write("/libCollect/show");
//        }
    }
    @RequestMapping("Logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/test")
    public String logintest(@RequestParam(required = false) String name, @RequestParam(required = false) String password){
        return "test";
    }
}
