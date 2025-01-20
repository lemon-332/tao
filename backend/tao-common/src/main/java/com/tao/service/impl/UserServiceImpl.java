package com.tao.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.tao.entity.po.User;
import com.tao.entity.query.UserQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.service.UserService;
import com.tao.mapper.UserMapper;
import com.tao.entity.query.SimplePage;
import com.tao.myEnum.PageSize;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc 
 */

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper<User,UserQuery> userMapper;
	/**
	 * 根据条件查询列表
	 */
	public List<User> findListByQuery(UserQuery query){
		return userMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByQuery(UserQuery query){
		return userMapper.selectCount(query);

	}

	/**
	 * 分页查询
	 */
	public PaginationResultVo<User> findListByPage(UserQuery query){
		int count = this.findCountByQuery(query);
		int pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();

		SimplePage page = new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<User> list = this.findListByQuery(query);
		PaginationResultVo<User> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(User bean){
		return userMapper.insert(bean);
	}

	/**
	 * 批量新增对象
	 */
	public Integer addBatch(List<User> listBean){
		if(listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return userMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增/修改对象
	 */
	public Integer addOrUpdateBatch(List<User> listBean){
		if(listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return userMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据UserId查询对象
	 */
	public User getUserByUserId(String userId){
		return userMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId更新对象
	 */
	public Integer updateUserByUserId(User bean, String userId){
		return userMapper.updateByUserId(bean ,userId);
	}

	/**
	 * 根据UserId删除对象
	 */
	public Integer deleteUserByUserId(String userId){
		return userMapper.deleteByUserId(userId);
	}


}