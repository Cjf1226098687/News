package com.fc.dao;

import com.fc.vo.MusicVo;

import java.util.List;

public interface RecommendDao {
    List<MusicVo> findAll();
}
