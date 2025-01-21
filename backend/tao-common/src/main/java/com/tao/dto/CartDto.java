package com.tao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tao.entity.po.God;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CartDto implements Serializable {
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
    public List<God> godList;
    /**
     * 购物车创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startTime;

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

    public List<God> getGodList() {
        return godList;
    }

    public void setGodList(List<God> godList) {
        this.godList = godList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
