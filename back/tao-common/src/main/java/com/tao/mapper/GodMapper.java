package com.tao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface GodMapper<T, P> extends BaseMapper {
    /**
     * 根据GodIdAndSellerId查询
     */
    T selectByGodIdAndSellerId(@Param("godId") String godId, @Param("sellerId") String sellerId);

    /**
     * 根据GodIdAndSellerId更新
     */
    Integer updateByGodIdAndSellerId(@Param("bean") T t, @Param("godId") String godId, @Param("sellerId") String sellerId);

    /**
     * 根据GodIdAndSellerId删除
     */
    Integer deleteByGodIdAndSellerId(@Param("godId") String godId, @Param("sellerId") String sellerId);

    List<T> selectBySellerId(String sellerId);
}