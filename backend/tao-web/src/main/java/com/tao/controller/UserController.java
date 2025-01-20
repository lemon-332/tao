package com.tao.controller;

import com.tao.annotation.GlobalInterceptor;
import com.tao.annotation.VerifyParam;
import com.tao.entity.po.User;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc
 */

@RestController
@RequestMapping("user")
public class UserController extends ABaseController {

    @Resource
    private UserService userService;

    /**
     * 登录
     */
    @RequestMapping("login")
    @GlobalInterceptor(checkParams = true)
    public ResponseVo login(@VerifyParam(required = true) String userName,
                            @VerifyParam(required = true) String password,
                            @VerifyParam(required = true) Integer role) {
        User user = userService.login(userName, password, role);
        return getSuccessResponseVo(user);
    }

    /**
     * 注册
     */
    @RequestMapping("register")
    // todo 增加登录验证
    @GlobalInterceptor(checkParams = true)
    public ResponseVo register(String userName, String phone, String password, String displayName, Integer role) {
        userService.register(userName, phone, password, displayName, role);
        return getSuccessResponseVo(null);
    }

}