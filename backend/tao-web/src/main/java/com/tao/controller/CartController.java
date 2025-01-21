package com.tao.controller;

import com.tao.dto.CartDto;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController()
@RequestMapping("cart")

public class CartController extends ABaseController {
    @Resource
    private CartService cartService;

    @RequestMapping("cartList")
    public ResponseVo cartList(String userId) {
        CartDto cartDto = cartService.cartList(userId);
        return getSuccessResponseVo(cartDto);
    }

    @RequestMapping("cartDeleteGod")
    public ResponseVo cartDeleteGod(String userId, String godIds) {
        cartService.cartDeleteGod(userId, godIds);
        return getSuccessResponseVo(null);
    }

    @RequestMapping("cartAddOrUpdateGod")
    public ResponseVo cartAddOrUpdateGod(String userId, String godIds) {
        cartService.cartAddGod(userId, godIds);
        return getSuccessResponseVo(null);
    }

}
