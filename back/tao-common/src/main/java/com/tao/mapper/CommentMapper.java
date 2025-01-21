package com.tao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface CommentMapper<T, P> extends BaseMapper {
    /**
     * 根据CommentId查询
     */
    T selectByCommentId(@Param("commentId") String commentId);

    /**
     * 根据CommentId更新
     */
    Integer updateByCommentId(@Param("bean") T t, @Param("commentId") String commentId);

    /**
     * 根据CommentId删除
     */
    Integer deleteByCommentId(@Param("commentId") String commentId);


}