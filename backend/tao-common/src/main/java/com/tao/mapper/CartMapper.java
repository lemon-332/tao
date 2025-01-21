package com.tao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface CartMapper<T, P> extends BaseMapper {
    /**
     * 根据CartIdAndUserIdAndGodIds查询
     */
    T selectByCartIdAndUserIdAndGodIds(@Param("cartId") String cartId, @Param("userId") String userId, @Param("godIds") String godIds);

    /**
     * 根据CartIdAndUserIdAndGodIds更新
     */
    Integer updateByCartIdAndUserIdAndGodIds(@Param("bean") T t, @Param("cartId") String cartId, @Param("userId") String userId, @Param("godIds") String godIds);

    /**
     * 根据CartIdAndUserIdAndGodIds删除
     */
    Integer deleteByCartIdAndUserIdAndGodIds(@Param("cartId") String cartId, @Param("userId") String userId, @Param("godIds") String godIds);

    T selectByUserId(String userId);
}