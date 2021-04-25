package com.fc.service.impl;

import com.fc.dao.MusicDao;
import com.fc.service.MusicService;
import com.fc.vo.MusicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    @Override
    public List<MusicVo> findByKeyword(String keyword) {
        return musicDao.findByKeyword("%" + keyword + "%");
    }
}
