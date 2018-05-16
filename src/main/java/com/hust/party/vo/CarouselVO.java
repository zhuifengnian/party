package com.hust.party.vo;

import com.hust.party.pojo.Carousel;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片轮播的VO<br/>
 * fan 2018/5/16 1:50
 */
public class CarouselVO {
    List<Carousel> carousels = new ArrayList<>();

    public List<Carousel> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<Carousel> carousels) {
        this.carousels = carousels;
    }
}