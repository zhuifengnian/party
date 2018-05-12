package com.hust.party.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wei on 2017/7/10.
 */
@Controller
public class ErrController {
	@RequestMapping("/err404")
	@ApiOperation(value = "处理404", httpMethod = "POST", notes = "教程")
	public ModelAndView handler404Request(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("err404");
		return mav;
	}

	@RequestMapping("/err500")
	@ApiOperation(value = "处理500", httpMethod = "POST", notes = "教程")
	public ModelAndView handler500Request(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("err500");
		return mav;
	}
}
