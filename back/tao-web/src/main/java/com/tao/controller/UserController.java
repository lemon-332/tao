package com.tao.controller;

import com.tao.annotation.GlobalInterceptor;
import com.tao.annotation.VerifyParam;
import com.tao.entity.po.User;
import com.tao.entity.query.UserQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.entity.vo.ResponseVo;
import com.tao.exception.BusinessException;
import com.tao.myEnum.ResponseCode;
import com.tao.service.UserService;
import com.tao.utils.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public ResponseVo register(@VerifyParam(required = true) String userName, String phone, String password, String displayName, @VerifyParam(required = true) Integer role) {
        userService.register(userName, phone, password, displayName, role);
        return getSuccessResponseVo(null);
    }

    @RequestMapping("logout")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVo logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
        }
        return getSuccessResponseVo(null);
    }

    @RequestMapping("reset")
    @GlobalInterceptor(checkLogin = true, checkParams = true)
    public ResponseVo reset(@VerifyParam(required = true) String userName) {
        String newPassword = userService.reset(userName);
        return getSuccessResponseVo(newPassword);
    }

    @RequestMapping("userList")
    // todo 登录校验
    public ResponseVo userList(String userNameFuzzy) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserNameFuzzy(userNameFuzzy);
        PaginationResultVo<User> listByPage = userService.findListByPage(userQuery);
        return getSuccessResponseVo(listByPage);
    }

    @RequestMapping("info/{userId}")
    // todo 登录校验
    public ResponseVo userInfo(@PathVariable("userId") String userId) {
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(ResponseCode.CODE_904);
        }
        return getSuccessResponseVo(user);
    }

    @RequestMapping("userUpdate")
    // todo 登录校验
    public ResponseVo userUpdate(User user) {
        userService.updateUserByUserId(user, user.getUserId());
        return getSuccessResponseVo(null);
    }

    @RequestMapping("userAdd")
    public ResponseVo userAdd(User user) {
        user.setUserId(StringUtils.random16());
        user.setStartTime(new Date());
        userService.add(user);
        return getSuccessResponseVo(null);
    }
}