package com.fc.controller;

import com.fc.bean.Navigation;
import com.fc.service.NavigationService;
import com.fc.vo.MusicVo;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("navigation")
public class NavigationController {
    @Autowired
    private NavigationService navigationService;

    // 获取导航栏
    @PostMapping("findAll")
    public ResultVo findAll() {
        List<Navigation> list = navigationService.findAll();

        if (list == null) {
            return ResultVo.fail("系统异常", 500);
        }

        return new ResultVo(list);
    }

    // 获取指定导航栏标题对应的歌曲
    @RequestMapping("findMusicsById")
    public ResultVo findMusicsById(Integer id) {
        List<MusicVo> list = navigationService.findMusicsById(id);

        if (list == null) {
            return ResultVo.fail("系统异常", 500);
        }

        if (list.size() == 0) {
            return ResultVo.success("请求成功，当前导航标题栏无内容", 205, null);
        }

        return new ResultVo(list);
    }
}
