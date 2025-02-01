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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("seller")
public class SellerController extends ABaseController {

    @Resource
    private SellerService sellerService;

    @RequestMapping("sellerList")
    public ResponseVo sellerList(String sellerNameFuzzy, String startTimeStart) {
        SellerQuery sellerQuery = new SellerQuery();
        sellerQuery.setSellerNameFuzzy(sellerNameFuzzy);
        sellerQuery.setStartTimeStart(startTimeStart);
        PaginationResultVo<Seller> resultVo = sellerService.findListByPage(sellerQuery);
        return getSuccessResponseVo(resultVo);
    }

    @RequestMapping("sellerNeedAgree")
    public ResponseVo sellerNeedAgree() {
        List<Seller> registerList = sellerService.findSellerRegisterList();
        registerList = registerList.stream().filter(seller -> seller.getSellerStatus() == 1).collect(Collectors.toList());
        return getSuccessResponseVo(registerList);
    }

    @RequestMapping("sellerRegisterList")
    public ResponseVo sellerRegisterList() {
        List<Seller> sellerList = sellerService.findSellerRegisterList();
        return getSuccessResponseVo(sellerList);
    }

    @RequestMapping("sellerAdd")
    public ResponseVo sellerAdd(Seller seller) {
        seller.setSellerId(StringUtils.random16());
        seller.setStartTime(new Date());
        seller.setSellerImage("default.png");
        seller.setGodIds("");
        seller.setSellerStatus(1);
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

    @RequestMapping("sellerInfo")
    public ResponseVo sellerInfo(String sellerId) {
        Seller seller = sellerService.getSellerBySellerId(sellerId);
        return getSuccessResponseVo(seller);
    }

    @RequestMapping("sellerAgree")
    public ResponseVo sellerAgree(String sellerId) {
        sellerService.sellerAgree(sellerId);
        return getSuccessResponseVo(null);
    }
}
