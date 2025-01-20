package com.tao.service;

import com.tao.entity.po.Comment;
import com.tao.entity.query.CommentQuery;
import com.tao.entity.vo.PaginationResultVo;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface CommentService {

    /**
     * 根据条件查询列表
     */
    public List<Comment> findListByQuery(CommentQuery query);

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(CommentQuery query);

    /**
     * 分页查询
     */
    public PaginationResultVo<Comment> findListByPage(CommentQuery query);

    /**
     * 新增
     */
    public Integer add(Comment bean);

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Comment> listBean);

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Comment> listBean);

    /**
     * 根据CommentId查询对象
     */
    public Comment getCommentByCommentId(String commentId);

    /**
     * 根据CommentId更新对象
     */
    public Integer updateCommentByCommentId(Comment bean, String commentId);

    /**
     * 根据CommentId删除对象
     */
    public Integer deleteCommentByCommentId(String commentId);


}