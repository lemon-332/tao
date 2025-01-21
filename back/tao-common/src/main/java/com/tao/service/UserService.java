package com.tao.service;

import com.tao.entity.po.User;
import com.tao.entity.query.UserQuery;
import com.tao.entity.vo.PaginationResultVo;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc
 */
public interface UserService {

    /**
     * 根据条件查询列表
     */
    public List<User> findListByQuery(UserQuery query);

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(UserQuery query);

    /**
     * 分页查询
     */
    public PaginationResultVo<User> findListByPage(UserQuery query);

    /**
     * 新增
     */
    public Integer add(User bean);

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<User> listBean);

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<User> listBean);

    /**
     * 根据UserId查询对象
     */
    public User getUserByUserId(String userId);

    /**
     * 根据UserId更新对象
     */
    public Integer updateUserByUserId(User bean, String userId);

    /**
     * 根据UserId删除对象
     */
    public Integer deleteUserByUserId(String userId);


    User login(String username, String password, Integer role);

    void register(String userName, String phone, String password, String displayName, Integer role);

    String reset(String userName);
}