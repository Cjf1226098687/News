package com.fc.service;

import com.fc.vo.CarouselVo;
import com.fc.vo.MusicVo;

import java.util.List;

public interface CarouselService {
    List<CarouselVo> findAll();

    MusicVo findMusicById(Integer musicId);
}
