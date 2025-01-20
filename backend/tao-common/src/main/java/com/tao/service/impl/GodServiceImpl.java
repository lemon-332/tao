package com.tao.service.impl;

import com.tao.entity.po.God;
import com.tao.entity.query.GodQuery;
import com.tao.entity.query.SimplePage;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.mapper.GodMapper;
import com.tao.myEnum.PageSize;
import com.tao.service.GodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */

@Service("GodService")
public class GodServiceImpl implements GodService {

    @Resource
    private GodMapper<God, GodQuery> godMapper;

    /**
     * 根据条件查询列表
     */
    public List<God> findListByQuery(GodQuery query) {
        return godMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(GodQuery query) {
        return godMapper.selectCount(query);

    }

    /**
     * 分页查询
     */
    public PaginationResultVo<God> findListByPage(GodQuery query) {
        int count = this.findCountByQuery(query);
        int pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();

        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<God> list = this.findListByQuery(query);
        PaginationResultVo<God> result = new PaginationResultVo(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(God bean) {
        return godMapper.insert(bean);
    }

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<God> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return godMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<God> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return godMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据GodIdAndSellerId查询对象
     */
    public God getGodByGodIdAndSellerId(String godId, String sellerId) {
        return godMapper.selectByGodIdAndSellerId(godId, sellerId);
    }

    /**
     * 根据GodIdAndSellerId更新对象
     */
    public Integer updateGodByGodIdAndSellerId(God bean, String godId, String sellerId) {
        return godMapper.updateByGodIdAndSellerId(bean, godId, sellerId);
    }

    /**
     * 根据GodIdAndSellerId删除对象
     */
    public Integer deleteGodByGodIdAndSellerId(String godId, String sellerId) {
        return godMapper.deleteByGodIdAndSellerId(godId, sellerId);
    }

}