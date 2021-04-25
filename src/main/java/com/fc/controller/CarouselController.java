package com.fc.controller;

import com.fc.service.CarouselService;
import com.fc.vo.CarouselVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping("findAll")
    public ResultVo findAll() {
        List<CarouselVo> list = carouselService.findAll();

        if (list == null) {
            return ResultVo.fail("服务器访问异常", 500);
        }

        return new ResultVo(list);
    }
}
