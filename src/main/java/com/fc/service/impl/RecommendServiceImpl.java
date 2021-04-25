package com.fc.service.impl;

import com.fc.dao.RecommendDao;
import com.fc.service.RecommendService;
import com.fc.vo.MusicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendDao recommendDao;

    @Override
    public List<MusicVo> findAll() {
        return recommendDao.findAll();
    }
}
