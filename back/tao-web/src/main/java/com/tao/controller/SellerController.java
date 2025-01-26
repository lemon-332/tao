package com.tao.controller;

import com.tao.entity.po.Seller;
import com.tao.entity.query.SellerQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.SellerService;
import com.tao.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("seller")
public class SellerController extends ABaseController {

    @Resource
    private SellerService sellerService;

    @RequestMapping("sellerList")
    public ResponseVo sellerList(String sellerName, String startTimeStart) {
        SellerQuery sellerQuery = new SellerQuery();
        sellerQuery.setSellerName(sellerName);
        sellerQuery.setStartTimeStart(startTimeStart);
        PaginationResultVo<Seller> resultVo = sellerService.findListByPage(sellerQuery);
        return getSuccessResponseVo(resultVo);
    }

    @RequestMapping("sellerAdd")
    public ResponseVo sellerAdd(Seller seller) {
        seller.setSellerId(StringUtils.random16());
        seller.setSellerStar(0);
        seller.setStartTime(new Date());
        seller.setSellerImage("default.png");
        seller.setGodIds("");
        sellerService.add(seller);
        return getSuccessResponseVo(null);
    }

    @RequestMapping("sellerUpdate")
    public ResponseVo sellerUpdate(Seller seller) {
        sellerService.updateSellerBySellerId(seller, seller.getSellerId());
        return getSuccessResponseVo(null);
    }

    @RequestMapping("sellerDelete")
    public ResponseVo sellerDelete(String sellerId) {
        sellerService.sellerDelete(sellerId);
        return getSuccessResponseVo(null);
    }
}
