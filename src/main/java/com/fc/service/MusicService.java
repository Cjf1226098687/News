package com.fc.service;

import com.fc.vo.MusicVo;

import java.util.List;

public interface MusicService {
    List<MusicVo> findByKeyword(String keyword);
}
