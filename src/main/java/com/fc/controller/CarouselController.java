package com.fc.controller;

import com.fc.service.CarouselService;
import com.fc.vo.CarouselVo;
import com.fc.vo.MusicVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @PostMapping("findAll")
    public ResultVo findAll() {
        List<CarouselVo> list = carouselService.findAll();

        if (list == null) {
            return ResultVo.fail("服务器访问异常", 500);
        }

        return new ResultVo(list);
    }

    @PostMapping("findMusicById")
    public ResultVo findMusicById(Integer musicId) {

        if (musicId == null) {
            return ResultVo.fail("音乐的id不能为空，请传入指定歌曲的id", 500);
        }

        MusicVo musicVo = carouselService.findMusicById(musicId);

        if (musicVo == null) {
            return ResultVo.fail("当前歌曲不存在或已失效", 404);
        }

        return new ResultVo(musicVo);
    }
}
