package com.hust.party.exception;

import com.hust.party.common.ReturnMessage;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于接收异常信息的切面，如需返回给前台异常信息，只需throw一个ApiException对象
 * fan
 */
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 自定义的异常类处理
     * @param e
     * @return
     */
    @ExceptionHandler(ApiExpection.class)
    @ResponseBody
    public ReturnMessage processApiException(ApiExpection e) {
        return new ReturnMessage(e.getCode(), e.getMessage()); //返回给前台异常信息
    }

    //参数类型不匹配
//getPropertyName()获取数据类型不匹配参数名称
//getRequiredType()实际要求客户端传递的数据类型
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ReturnMessage requestTypeMismatch(TypeMismatchException ex){
        return new ReturnMessage(400, "参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType());
    }
    //缺少参数异常
//getParameterName() 缺少的参数名称
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ReturnMessage requestMissingServletRequest(MissingServletRequestParameterException ex){
        String message = "缺少必要参数,参数名称为" + ex.getParameterName();
        return new ReturnMessage(400, message);
    }
}
