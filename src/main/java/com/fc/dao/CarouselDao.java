package com.fc.dao;

import com.fc.vo.CarouselVo;
import com.fc.vo.MusicVo;

import java.util.List;

public interface CarouselDao {
    List<CarouselVo> findAll();

    MusicVo findMusicById(Integer musicId);
}
