package com.fc.controller;

import com.fc.service.MusicService;
import com.fc.vo.MusicVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @PostMapping("findByKeyword")
    public ResultVo findByKeyword(String keyword) {
        List<MusicVo> list = musicService.findByKeyword(keyword);

        return new ResultVo(list);
    }
}
