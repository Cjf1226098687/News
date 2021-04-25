package com.fc.dao;

import com.fc.vo.CarouselVo;

import java.util.List;

public interface CarouselDao {
    List<CarouselVo> findAll();
}
