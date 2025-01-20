package com.tao.mapper;

import org.apache.ibatis.annotations.Param;
/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc 
 */
public interface UserMapper<T, P> extends BaseMapper {
	/**
	 * 根据UserId查询
	 */
	T selectByUserId(@Param("userId") String userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateByUserId(@Param("bean") T t, @Param("userId") String userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteByUserId(@Param("userId") String userId);


}