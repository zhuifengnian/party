package com.hust.party.serviceimpl;

import com.hust.party.common.Page;
import com.hust.party.dao.BaseMapper;
import com.hust.party.dao.CarouselMapper;
import com.hust.party.pojo.Carousel;
import com.hust.party.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * fan 2018/5/16 1:39
 */
@Service
public class CarouselServiceImpl extends AbstractBaseServiceImpl<Carousel> implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public BaseMapper<Carousel> getDao() {
        return carouselMapper;
    }
}