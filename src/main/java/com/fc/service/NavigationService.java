package com.fc.service;

import com.fc.bean.Navigation;
import com.fc.vo.MusicVo;

import java.util.List;

public interface NavigationService {
    List<Navigation> findAll();

    List<MusicVo> findMusicsById(Integer id);
}
