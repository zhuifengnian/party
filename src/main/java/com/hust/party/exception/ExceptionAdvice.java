package com.hust.party.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author:王焕
 * @Date:2017/7/13
 */
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ApiExpection.class)
    public ModelAndView processApiException(HttpServletRequest request, HttpServletResponse response,ApiExpection e) {
        ModelAndView mv=new ModelAndView("err");
        mv.addObject("code",e.getCode());
        mv.addObject("message",e.getMessage());
        return mv; //返回一个逻辑视图名
    }
}
