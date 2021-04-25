package com.fc.service.impl;

import com.fc.dao.CarouselDao;
import com.fc.service.CarouselService;
import com.fc.vo.CarouselVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;

    @Override
    public List<CarouselVo> findAll() {
        return carouselDao.findAll();
    }
}
