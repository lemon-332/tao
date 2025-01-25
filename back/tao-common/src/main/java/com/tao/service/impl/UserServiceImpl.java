package com.tao.service.impl;

import com.tao.entity.po.User;
import com.tao.entity.query.SimplePage;
import com.tao.entity.query.UserQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.exception.BusinessException;
import com.tao.mapper.UserMapper;
import com.tao.myEnum.PageSize;
import com.tao.myEnum.ResponseCode;
import com.tao.myEnum.UserStatus;
import com.tao.service.UserService;
import com.tao.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc
 */

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper<User, UserQuery> userMapper;


    @Override
    public User login(String userName, String password, Integer role) {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new BusinessException(ResponseCode.CODE_904);
        }
        if (!user.getRole().equals(role)) {
            throw new BusinessException("用户角色不匹配");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException(ResponseCode.CODE_902);
        }
        if (user.getStatus().equals(UserStatus.DEACTIVE.getUserStatus())) {
            throw new BusinessException(ResponseCode.CODE_903);
        }
        return user;
    }

    @Override
    public void register(String userName, String phone, String password, String displayName, Integer role) {
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            throw new BusinessException(ResponseCode.CODE_905);
        }
        user = new User();
        user.setUserName(userName);
        user.setUserId(StringUtils.random16());
        user.setPhone(phone);
        user.setPassword(password);
        user.setDisplayName(displayName);
        user.setRole(role);
        user.setStatus(UserStatus.ACTIVE.getUserStatus());
        user.setStartTime(new Date());
        userMapper.insert(user);
    }

    @Override
    public String reset(String userName) {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new BusinessException(ResponseCode.CODE_904);
        }
        String newPassword = StringUtils.random16();
        while (newPassword.equals(user.getPassword())) {
            newPassword = StringUtils.random16();
        }
        return newPassword;
    }

    /**
     * 根据条件查询列表
     */
    public List<User> findListByQuery(UserQuery query) {
        return userMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(UserQuery query) {
        return userMapper.selectCount(query);

    }

    /**
     * 分页查询
     */
    public PaginationResultVo<User> findListByPage(UserQuery query) {
        int count = this.findCountByQuery(query);
        int pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();

        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<User> list = this.findListByQuery(query);
        PaginationResultVo<User> result = new PaginationResultVo(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(User bean) {
        return userMapper.insert(bean);
    }

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<User> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return userMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<User> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return userMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据UserId查询对象
     */
    public User getUserByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId更新对象
     */
    public Integer updateUserByUserId(User bean, String userId) {
        if (bean.getStatus().equals(UserStatus.DEACTIVE.getUserStatus())) {
            bean.setDisableTime(new Date());
        }
        return userMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除对象
     */
    public Integer deleteUserByUserId(String userId) {
        return userMapper.deleteByUserId(userId, UserStatus.DELETE.getUserStatus());
    }


}