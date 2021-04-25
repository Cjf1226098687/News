package com.fc.controller;

import com.fc.service.RecommendService;
import com.fc.vo.MusicVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @PostMapping("findAll")
    public ResultVo findAll() {
        List<MusicVo> list = recommendService.findAll();

        return new ResultVo(list);
    }
}
