package com.tao.service.impl;

import com.tao.entity.po.Comment;
import com.tao.entity.query.CommentQuery;
import com.tao.entity.query.SimplePage;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.mapper.CommentMapper;
import com.tao.myEnum.PageSize;
import com.tao.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper<Comment, CommentQuery> commentMapper;

    /**
     * 根据条件查询列表
     */
    public List<Comment> findListByQuery(CommentQuery query) {
        return commentMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(CommentQuery query) {
        return commentMapper.selectCount(query);

    }

    /**
     * 分页查询
     */
    public PaginationResultVo<Comment> findListByPage(CommentQuery query) {
        int count = this.findCountByQuery(query);
        int pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();

        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<Comment> list = this.findListByQuery(query);
        PaginationResultVo<Comment> result = new PaginationResultVo(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(Comment bean) {
        return commentMapper.insert(bean);
    }

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Comment> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return commentMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Comment> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return commentMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据CommentId查询对象
     */
    public Comment getCommentByCommentId(String commentId) {
        return commentMapper.selectByCommentId(commentId);
    }

    /**
     * 根据CommentId更新对象
     */
    public Integer updateCommentByCommentId(Comment bean, String commentId) {
        return commentMapper.updateByCommentId(bean, commentId);
    }

    /**
     * 根据CommentId删除对象
     */
    public Integer deleteCommentByCommentId(String commentId) {
        return commentMapper.deleteByCommentId(commentId);
    }


}