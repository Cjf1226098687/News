package com.fc.dao;

import com.fc.bean.User;
import com.fc.vo.UserInfoVo;
import com.fc.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<UserVo> getUser();

    // 根据电话获取手机验证码
    String getCodeByPhone(@Param("phone") String phone);

    // 刷新手机验证码
    int updateCode(@Param("phone") String phone, @Param("code") String code);

    // 使用手机号和验证码进行登录
    UserVo login(@Param("phone") String phone, @Param("code") String code);

    // 使用手机号和密码进行注册
    int register(User user);

    // 使用手机号和密码进行登录
    UserVo loginByPassword(@Param("phone") String phone, @Param("password") String password);

    // 根据手机号获取用户
    UserVo getPhone(@Param("phone") String phone);

    // 根据id获取用户信息
    UserInfoVo getUserInfoById(@Param("id") Integer id);

    // 根据id修改用户信息
    int updateById(User user);

    // 根据id获取用户
    UserVo getUserById(@Param("id") Integer id);
}
