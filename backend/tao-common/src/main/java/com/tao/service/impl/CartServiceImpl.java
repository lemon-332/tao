package com.tao.service.impl;

import com.tao.entity.po.Cart;
import com.tao.entity.query.CartQuery;
import com.tao.entity.query.SimplePage;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.mapper.CartMapper;
import com.tao.myEnum.PageSize;
import com.tao.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */

@Service("CartService")
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper<Cart, CartQuery> cartMapper;

    /**
     * 根据条件查询列表
     */
    public List<Cart> findListByQuery(CartQuery query) {
        return cartMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(CartQuery query) {
        return cartMapper.selectCount(query);

    }

    /**
     * 分页查询
     */
    public PaginationResultVo<Cart> findListByPage(CartQuery query) {
        int count = this.findCountByQuery(query);
        int pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();

        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<Cart> list = this.findListByQuery(query);
        PaginationResultVo<Cart> result = new PaginationResultVo(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(Cart bean) {
        return cartMapper.insert(bean);
    }

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Cart> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return cartMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Cart> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return cartMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据CartIdAndUserIdAndGodIds查询对象
     */
    public Cart getCartByCartIdAndUserIdAndGodIds(String cartId, String userId, String godIds) {
        return cartMapper.selectByCartIdAndUserIdAndGodIds(cartId, userId, godIds);
    }

    /**
     * 根据CartIdAndUserIdAndGodIds更新对象
     */
    public Integer updateCartByCartIdAndUserIdAndGodIds(Cart bean, String cartId, String userId, String godIds) {
        return cartMapper.updateByCartIdAndUserIdAndGodIds(bean, cartId, userId, godIds);
    }

    /**
     * 根据CartIdAndUserIdAndGodIds删除对象
     */
    public Integer deleteCartByCartIdAndUserIdAndGodIds(String cartId, String userId, String godIds) {
        return cartMapper.deleteByCartIdAndUserIdAndGodIds(cartId, userId, godIds);
    }

}