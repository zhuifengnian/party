package com.hust.party.controller;

import com.hust.party.common.Page;
import com.hust.party.common.ReturnMessage;
import com.hust.party.exception.ApiExpection;
import com.hust.party.pojo.Carousel;
import com.hust.party.service.CarouselService;
import com.hust.party.vo.CarouselVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 图片轮播的controller<br/>
 * fan 2018/5/16 1:31
 */
@Controller
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    /**
     * 图片轮播的接口，默认展示三张图片
     * @param num 轮播图片的数量
     */
    @ApiOperation(value = "获取图片轮播的接口", httpMethod = "GET")
    @RequestMapping(value = "/pictureScroller", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMessage show(@ApiParam(required = false, name = "num", value = "展示数量，默认为3")
                                  @RequestParam(required = false, defaultValue = "3") Integer num){
        //先去查询所有的轮播,并对数量做处理
        int totalNum = carouselService.selectCount(null);
        if(num < 0){
            throw new ApiExpection(201, "图片数量需要为正数");
        }
        if(num > totalNum){
            num = totalNum;
        }
        //获取所有轮播数据
        Page page = new Page();
        page.setPageNumber(1);
        page.setPageSize(num);
        page.setStart(1);
        List<Carousel> carousels = carouselService.select(null, page);
        CarouselVO carouselVO = new CarouselVO();
        carouselVO.setCarousels(carousels);
        return new ReturnMessage(200, carouselVO);
    }
}