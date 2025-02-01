package com.tao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-26 11:57
 * @desc
 */
public interface SellerMapper<T, P> extends BaseMapper {
    /**
     * 根据SellerId查询
     */
    T selectBySellerId(@Param("sellerId") String sellerId);

    /**
     * 根据SellerId更新
     */
    Integer updateBySellerId(@Param("bean") T t, @Param("sellerId") String sellerId);

    /**
     * 根据SellerId删除
     */
    Integer deleteBySellerId(@Param("sellerId") String sellerId);


    List<T> selectRegisterList(@Param("query") P p);

    void sellerAgree(String sellerId);
}