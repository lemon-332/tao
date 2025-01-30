package com.tao.service.impl;

import com.tao.entity.po.God;
import com.tao.entity.po.Seller;
import com.tao.entity.query.GodQuery;
import com.tao.entity.query.SellerQuery;
import com.tao.entity.query.SimplePage;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.exception.BusinessException;
import com.tao.mapper.GodMapper;
import com.tao.mapper.SellerMapper;
import com.tao.myEnum.PageSize;
import com.tao.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon
 * @date 2025-01-26 11:57
 * @desc
 */

@Service("SellerService")
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerMapper<Seller, SellerQuery> sellerMapper;

    @Resource
    private GodMapper<God, GodQuery> godMapper;

    @Override
    public void sellerDelete(String sellerId) {
        List<God> gods = godMapper.selectBySellerId(sellerId);
        if (!gods.isEmpty()) {
            throw new BusinessException("该商家下有商品，不能删除");
        }
        sellerMapper.deleteBySellerId(sellerId);
    }

    @Override
    public List<Seller> findSellerRegisterList() {
        List<Seller> sellerList = sellerMapper.selectRegisterList(new SellerQuery());
        return sellerList;
    }

    /**
     * 根据条件查询列表
     */
    public List<Seller> findListByQuery(SellerQuery query) {
        return sellerMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByQuery(SellerQuery query) {
        return sellerMapper.selectCount(query);

    }

    /**
     * 分页查询
     */
    public PaginationResultVo<Seller> findListByPage(SellerQuery query) {
        int count = this.findCountByQuery(query);
        int pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();

        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<Seller> list = this.findListByQuery(query);
        PaginationResultVo<Seller> result = new PaginationResultVo(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(Seller bean) {
        return sellerMapper.insert(bean);
    }

    /**
     * 批量新增对象
     */
    public Integer addBatch(List<Seller> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return sellerMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改对象
     */
    public Integer addOrUpdateBatch(List<Seller> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return sellerMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据SellerId查询对象
     */
    public Seller getSellerBySellerId(String sellerId) {
        return sellerMapper.selectBySellerId(sellerId);
    }

    /**
     * 根据SellerId更新对象
     */
    public Integer updateSellerBySellerId(Seller bean, String sellerId) {
        return sellerMapper.updateBySellerId(bean, sellerId);
    }

    /**
     * 根据SellerId删除对象
     */
    public Integer deleteSellerBySellerId(String sellerId) {
        return sellerMapper.deleteBySellerId(sellerId);
    }


}