package com.fc.service.impl;

import com.fc.bean.User;
import com.fc.bean.UserInfo;
import com.fc.dao.UserDao;
import com.fc.service.UserService;
import com.fc.utils.JwtUtils;
import com.fc.vo.UserAuthVo;
import com.fc.vo.UserInfoVo;
import com.fc.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<UserVo> getUser(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        List<UserVo> list = userDao.getUser();

        return new PageInfo<>(list);
    }

    @Override
    public String getCodeByPhone(String phone) {
        String oldCode = userDao.getCodeByPhone(phone);

        if (oldCode != null && !oldCode.equals("")) {
            // 生成6为随机数手机验证码
            String newCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

            // 刷新数据库中的验证码
            int affectedRows = userDao.updateCode(phone, newCode);

            // 刷新成功返回
            if (affectedRows > 0) {
                return newCode;
            }

            return null;
        }

        return null;
    }

    @Override
    public UserAuthVo login(String phone, String code) {
        UserVo userVo = userDao.login(phone, code);

        if (userVo == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        // 添加电话，方便拦截器进行获取
        map.put("phone", phone);

        String token = JwtUtils.getToken(map, 30);

        UserAuthVo userAuthVo = new UserAuthVo(userVo);

        userAuthVo.setToken(token);

        return userAuthVo;
    }

    @Override
    public UserVo register(String phone, String password) {
        UserVo userVo = userDao.getPhone(phone);

        // 查询结果不为空，说明当前用户已存在
        if (userVo != null) {
            return null;
        }

        User user = new User();
        user.setName("系统默认用户名");
        user.setAge(18);
        user.setAvaUrl("https://cdnmusic.migu.cn/v3/static/img/common/no-login.png");
        user.setGender((byte) 2);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCode("123456");

        int affectedRows = userDao.register(user);

        if (affectedRows > 0) {
            return new UserVo(user.getId(), user.getName(), user.getPhone(), user.getAge(), user.getGender(), user.getAvaUrl());
        }

        return null;
    }

    @Override
    public UserVo loginByPassword(String phone, String password) {
        UserVo userVo = userDao.loginByPassword(phone, password);

        if (userVo == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        // 添加电话，方便拦截器进行获取
        map.put("phone", phone);

        String token = JwtUtils.getToken(map, 30);

        UserAuthVo userAuthVo = new UserAuthVo(userVo);

        userAuthVo.setToken(token);

        return userAuthVo;
    }

    // 根据id获取用户详情
    @Override
    public UserInfoVo getCheckDetailById(Integer id) {
        return userDao.getUserInfoById(id);
    }

    // 根据id修改用户信息
    @Override
    public UserVo updateUserById(User user) {
        UserVo userVo = new UserVo();

        // 先判断数据库中有没有这个用户
        if (userDao.getUserById(user.getId()) == null) {
            userVo.setId(-1);

            return userVo;
        }

        // 如果传递了电话号码，就需要判断修改的电话号码是否已经存在
        if (null != user.getPhone()) {
            if (null != userDao.getPhone(user.getPhone())) {
                userVo.setPhone(null);
                return userVo;
            }
        }

        // 根据id修改用户信息
        int affectedRows = userDao.updateById(user);

        if (affectedRows > 0) {
            // 修改成功
            userVo = userDao.getUserById(user.getId());

            return userVo;
        }

        // 修改失败
        return null;
    }
}
