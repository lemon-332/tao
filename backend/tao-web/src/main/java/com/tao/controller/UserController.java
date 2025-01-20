package com.tao.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.tao.entity.po.User;
import com.tao.entity.query.UserQuery;
import com.tao.service.UserService;
import com.tao.entity.vo.ResponseVo;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc 
 */

@RestController
@RequestMapping("user")
public class UserController extends ABaseController{

	@Resource
	private UserService userService;
	/**
	 * 根据条件分页查询列表
	 */
	@RequestMapping("loadDataList")
	public ResponseVo loadDataList(UserQuery query){
		return getSuccessResponseVo("haha");
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVo add(User bean){
		userService.add(bean);
		return getSuccessResponseVo(null);
	}
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVo addBatch(@RequestBody List<User> listBean){
		userService.addBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 批量新增/修改对象
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<User> listBean){
		userService.addOrUpdateBatch(listBean);
		return getSuccessResponseVo(null);
	}

	/**
	 * 根据UserId查询对象
	 */
	@RequestMapping("getUserByUserId")
	public ResponseVo getUserByUserId(String userId){
		return getSuccessResponseVo(userService.getUserByUserId(userId));
	}

	/**
	 * 根据UserId更新对象
	 */
	@RequestMapping("updateUserByUserId")
	public ResponseVo updateUserByUserId(User bean, String userId){
		userService.updateUserByUserId(bean, userId);
		return getSuccessResponseVo(null);
	}

	/**
	 * 根据UserId删除对象
	 */
	@RequestMapping("deleteUserByUserId")
	public ResponseVo deleteUserByUserId(String userId){
		userService.deleteUserByUserId(userId);
		return getSuccessResponseVo(null);
	}


}