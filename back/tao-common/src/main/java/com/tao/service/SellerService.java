package com.tao.service;

import com.tao.entity.po.Seller;
import com.tao.entity.query.SellerQuery;
import com.tao.entity.vo.PaginationResultVo;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-26 11:57
 * @desc
 */
public interface SellerService {

    /**
     * 根据条件查询列表
     */
    public List<Seller> findListByQuery(SellerQuery query);

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(SellerQuery query);

    /**
     * 分页查询
     */
    public PaginationResultVo<Seller> findListByPage(SellerQuery query);

    /**
     * 新增
     */
    public Integer add(Seller bean);

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Seller> listBean);

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Seller> listBean);

    /**
     * 根据SellerId查询对象
     */
    public Seller getSellerBySellerId(String sellerId);

    /**
     * 根据SellerId更新对象
     */
    public Integer updateSellerBySellerId(Seller bean, String sellerId);

    /**
     * 根据SellerId删除对象
     */
    public Integer deleteSellerBySellerId(String sellerId);


    void sellerDelete(String sellerId);

    List<Seller> findSellerRegisterList();

    void sellerAgree(String sellerId);
}