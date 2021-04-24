package com.fc.service;

import com.fc.bean.User;
import com.fc.bean.UserInfo;
import com.fc.vo.UserInfoVo;
import com.fc.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface UserService {
    PageInfo<UserVo> getUser(Integer pageNo, Integer pageSize);

    String getCodeByPhone(String phone);

    UserVo login(String phone, String code);

    UserVo register(String phone, String password);

    UserVo loginByPassword(String phone, String password);

    UserInfoVo getCheckDetailById(Integer id);

    UserVo updateUserById(User user);
}
