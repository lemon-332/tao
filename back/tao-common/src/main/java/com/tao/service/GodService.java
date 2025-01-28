package com.tao.service;

import com.tao.entity.po.God;
import com.tao.entity.query.GodQuery;
import com.tao.entity.vo.PaginationResultVo;

import java.util.List;
import java.util.Map;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public interface GodService {

    /**
     * 根据条件查询列表
     */
    public List<God> findListByQuery(GodQuery query);

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(GodQuery query);

    /**
     * 分页查询
     */
    public PaginationResultVo<God> findListByPage(GodQuery query);

    /**
     * 新增
     */
    public Integer add(God bean);

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<God> listBean);

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<God> listBean);

    /**
     * 根据GodIdAndSellerId查询对象
     */
    public God getGodByGodIdAndSellerId(String godId, String sellerId);

    /**
     * 根据GodIdAndSellerId更新对象
     */
    public Integer updateGodByGodIdAndSellerId(God bean, String godId, String sellerId);

    /**
     * 根据GodIdAndSellerId删除对象
     */
    public Integer deleteGodByGodIdAndSellerId(String godId, String sellerId);

    Map<Integer, List<Integer>> statisticCount(List<God> godList);
}