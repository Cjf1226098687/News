package com.fc.controller;

import com.fc.bean.User;
import com.fc.bean.UserInfo;
import com.fc.service.UserService;
import com.fc.utils.RedisUtils;
import com.fc.vo.ResultVo;
import com.fc.vo.UserInfoVo;
import com.fc.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据电话号码获取手机验证码
     *
     * @param phone 电话号码
     * @return 响应视图对象
     */
    @GetMapping("getCode")
    @ResponseBody
    public ResultVo getCode(@RequestParam("phone") String phone) {
        String code = userService.getCodeByPhone(phone);

        if (code == null) {
            return new ResultVo("当前手机号不存在", 404, false, null);
        }

        return new ResultVo(code);
    }

    /**
     * 登录
     *
     * @param phone 手机号
     * @param code  手机验证码
     * @return 响应视图对象
     */
    @PostMapping("login")
    @ResponseBody
    public ResultVo loginByCode(String phone, String code, HttpServletRequest request) {

        UserVo userVo = userService.login(phone, code);

        if (userVo == null) {
            return new ResultVo("登录失败，账号或验证码错误", -1, false, null);
        }

        return new ResultVo(userVo);
    }

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return 响应视图对象
     */
    @PostMapping("loginByPassword")
    @ResponseBody
    public ResultVo loginByPassword(String phone, String password, HttpServletRequest request) {

        UserVo userVo = userService.loginByPassword(phone, password);

        if (userVo == null) {
            return new ResultVo("登录失败，账号或密码错误", -1, false, null);
        }

        return new ResultVo(userVo);
    }

    /**
     * 使用手机号和密码进行注册
     *
     * @param phone    手机号
     * @param password 密码
     * @return 响应视图对象
     */
    @PostMapping("register")
    @ResponseBody
    public ResultVo register(String phone, String password) {

        if(password == null || password.equals("")) {
            return new ResultVo("注册失败，密码不能为空", -1, false, null);
        }

        UserVo userVo = userService.register(phone, password);

        if (userVo == null) {
            return new ResultVo("注册失败，当前手机号已存在", -1, false, null);
        }

        return new ResultVo(userVo);
    }

    /**
     * 退出登录
     * @return 响应视图对象
     */
    @PostMapping("logout")
    @ResponseBody
    public ResultVo logout() {
        return new ResultVo("退出登录，token已清除");
    }

    /**
     * 获取用户列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示的条数
     * @return 响应视图对象
     */
    @PostMapping("getUserList")
    @ResponseBody
    public ResultVo getUserList(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<UserVo> pageInfo = userService.getUser(pageNum, pageSize);

        return new ResultVo(pageInfo);
    }

    /**
     * 根据id获取用户详情
     *
     * @param id 用户id
     * @return 响应视图对象
     */
    @PostMapping("getCheckDetailById")
    @ResponseBody
    public ResultVo getCheckDetailById(Integer id) {
        if (id == null) {
            return new ResultVo("请求异常，id不能为空", 500, false, null);
        }

        UserInfoVo userInfoVo = userService.getCheckDetailById(id);

        if (userInfoVo == null) {
            return new ResultVo("请求异常，当前id不存在", 500, false, null);
        }

        return new ResultVo(userInfoVo);
    }

    /**
     * 根据id修改用户信息
     *
     * @param user User对象
     * @return 响应视图对象
     */
    @PostMapping("updateCheckById")
    @ResponseBody
    public ResultVo updateCheckById(User user) {
        ResultVo resultVo = new ResultVo("请求异常，请传入正确的id", 500, false, null);

        // 没有id，直接返回
        if (null == user || user.getId() == null || user.getId() < 1) {
            return resultVo;
        } else if (user.getId() <= 50) {
            resultVo.setMessage("请求异常，没有权限操作该用户");
            return resultVo;
        }

        UserVo userVo = userService.updateUserById(user);

        // 修改失败
        if (userVo == null) {
            resultVo.setMessage("请求异常，修改用户信息失败");
            return resultVo;
        }

        // id对应的用户不存在
        if (userVo.getId() != null && userVo.getId() == -1) {
            resultVo.setMessage("请求异常，当前id对应的用户不存在");
            return resultVo;
        }

        // 电话号码重复
        if (userVo.getPhone() == null) {
            resultVo.setMessage("请求异常，该电话号码已存在");
            return resultVo;
        }

        // 请求成功
        resultVo.setSuccess(true);
        resultVo.setCode(200);
        resultVo.setMessage("请求成功");
        resultVo.setData(userVo);

        return resultVo;
    }
}
