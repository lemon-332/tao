package com.tao.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lemon
 * @date 2025-01-31 18:13
 * @desc
 */
public class Cart implements Serializable {
    /**
     * 购物车id
     */
    public String cartId;
    /**
     * 用户id
     */
    public String userId;
    /**
     * 多个商品id
     */
    public String godIds;
    /**
     * 购物车创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startTime;
    /**
     * 购物车状态( 0:待发货，1：待付款，2：已取消，3：交易完成)
     */
    public Integer cartStatus;
    /**
     * 每个商品对应的购买个数
     */
    public String godBoughtCount;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGodIds() {
        return godIds;
    }

    public void setGodIds(String godIds) {
        this.godIds = godIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Integer cartStatus) {
        this.cartStatus = cartStatus;
    }

    public String getGodBoughtCount() {
        return godBoughtCount;
    }

    public void setGodBoughtCount(String godBoughtCount) {
        this.godBoughtCount = godBoughtCount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId = " + cartId +
                ", userId = " + userId +
                ", godIds = " + godIds +
                ", startTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime) +
                ", cartStatus = " + cartStatus +
                ", godBoughtCount = " + godBoughtCount +
                '}';
    }
}