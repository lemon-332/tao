package com.tao.service.impl;

import com.tao.dto.CartDto;
import com.tao.dto.CartInfoDto;
import com.tao.dto.GodInfoDto;
import com.tao.entity.po.Cart;
import com.tao.entity.po.God;
import com.tao.entity.po.User;
import com.tao.entity.query.CartQuery;
import com.tao.entity.query.GodQuery;
import com.tao.entity.query.SimplePage;
import com.tao.entity.query.UserQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.exception.BusinessException;
import com.tao.mapper.CartMapper;
import com.tao.mapper.GodMapper;
import com.tao.mapper.UserMapper;
import com.tao.myEnum.PageSize;
import com.tao.service.CartService;
import com.tao.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private GodMapper<God, GodQuery> godMapper;

    @Resource
    private UserMapper<User, UserQuery> userMapper;

    @Override
    public List<CartInfoDto> findListByQueryToInfo(CartQuery cartQuery) {
        List<CartInfoDto> results = new ArrayList<>();
        List<Cart> list = cartMapper.selectList(cartQuery);
        for (Cart cart : list) {
            CartInfoDto cartInfoDto = new CartInfoDto();
            List<GodInfoDto> godInfoDtos = new ArrayList<>();
            String godBoughtCount = cart.getGodBoughtCount();
            String cartId = cart.getCartId();
            String userId = cart.getUserId();
            String godIds = cart.getGodIds();
            Integer cartStatus = cart.getCartStatus();
            Date startTime = cart.getStartTime();
            int godTotalPrice = 0;
            cartInfoDto.setCartId(cartId);
            User user = userMapper.selectByUserId(userId);
            cartInfoDto.setUserName(user.getDisplayName());
            cartInfoDto.setStartTime(startTime);
            cartInfoDto.setCartStatus(cartStatus);
            String[] godIdList = godIds.split(",");
            String[] boughtCount = godBoughtCount.split(",");

            for (int i = 0; i < godIdList.length; i++) {
                GodInfoDto godInfoDto = new GodInfoDto();
                God god = godMapper.selectByGodId(godIdList[i]);
                BigDecimal godPrice = god.getGodPrice();
                int godBought = Integer.parseInt(boughtCount[i]);
                godInfoDto.setGodId(god.getGodId());
                godInfoDto.setGodName(god.getGodName());
                godInfoDto.setGodPrice(godPrice);
                godInfoDto.setGodImg(god.getGodImage());
                godInfoDto.setGodCount(godBought);
                godTotalPrice += godPrice.multiply(new BigDecimal(godBought)).intValue();
                godInfoDtos.add(godInfoDto);
            }
            cartInfoDto.setTotalPrice(godTotalPrice);
            cartInfoDto.setGodInfoDtos(godInfoDtos);
            results.add(cartInfoDto);
        }
        return results;
    }

    @Override
    public CartDto cartList(String userId) {
        CartDto cartDto = new CartDto();
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            throw new BusinessException("购物车不存在");
        }
        cartDto.setUserId(userId);
        cartDto.setCartId(cartDto.getCartId());
        String godIds = cart.getGodIds();
        List<God> gods = new ArrayList<>();
        if (!godIds.isEmpty()) {
            String[] godList = godIds.split(",");
            for (String godId : godList) {
                God god = godMapper.selectByGodId(godId);
                if (god != null)
                    gods.add(god);
            }
        }
        cartDto.setGodList(gods);
        return cartDto;
    }

    @Override
    public void cartDeleteGod(String userId, String godIds) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            throw new BusinessException("购物车不存在");
        }
        cart.setGodIds(godIds);
        cartMapper.insertOrUpdate(cart);

    }

    @Override
    public void cartAddGod(String userId, String godIds) {
        Cart cart = cartMapper.selectByUserId(userId);
        if (cart == null) {
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            cart1.setCartId(StringUtils.random16());
            cart1.setStartTime(new Date());
            cart1.setGodIds(godIds);
            cartMapper.insert(cart1);
        } else {
            String godIds1 = cart.getGodIds();
            godIds1 = godIds1 + "," + godIds;
            cart.setGodIds(godIds1);
            cartMapper.insertOrUpdate(cart);
        }
    }

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