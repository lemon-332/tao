package com.tao.service;

import com.tao.dto.CartDto;
import com.tao.dto.CartInfoDto;
import com.tao.entity.po.Cart;
import com.tao.entity.query.CartQuery;
import com.tao.entity.vo.PaginationResultVo;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface CartService {

    /**
     * 根据条件查询列表
     */
    public List<Cart> findListByQuery(CartQuery query);

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(CartQuery query);

    /**
     * 分页查询
     */
    public PaginationResultVo<Cart> findListByPage(CartQuery query);

    /**
     * 新增
     */
    public Integer add(Cart bean);

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Cart> listBean);

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Cart> listBean);

    /**
     * 根据CartIdAndUserIdAndGodIds查询对象
     */
    public Cart getCartByCartIdAndUserIdAndGodIds(String cartId, String userId, String godIds);

    /**
     * 根据CartIdAndUserIdAndGodIds更新对象
     */
    public Integer updateCartByCartIdAndUserIdAndGodIds(Cart bean, String cartId, String userId, String godIds);

    /**
     * 根据CartIdAndUserIdAndGodIds删除对象
     */
    public Integer deleteCartByCartIdAndUserIdAndGodIds(String cartId, String userId, String godIds);

    CartDto cartList(String userId);

    void cartDeleteGod(String userId, String godIds);

    void cartAddGod(String userId, String godId);

    List<CartInfoDto> findListByQueryToInfo(CartQuery cartQuery);
}