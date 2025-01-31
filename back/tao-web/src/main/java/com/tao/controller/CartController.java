package com.tao.controller;

import com.tao.dto.CartInfoDto;
import com.tao.entity.query.CartQuery;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("cart")

public class CartController extends ABaseController {
    @Resource
    private CartService cartService;

    @RequestMapping("cartList")
    public ResponseVo cartList(String startTimeStart) {
        CartQuery cartQuery = new CartQuery();
        cartQuery.setStartTimeStart(startTimeStart);
        List<CartInfoDto> carts = cartService.findListByQueryToInfo(cartQuery);
        return getSuccessResponseVo(carts);
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
