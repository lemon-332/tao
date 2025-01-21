package com.tao.entity.query;

import java.util.Date;

public class CartQuery extends BaseParam {
    /**
     * 购物车id
     */
    public String cartId;

    public String cartIdFuzzy;
    /**
     * 用户id
     */
    public String userId;

    public String userIdFuzzy;
    /**
     * 多个商品id
     */
    public String godIds;

    public String godIdsFuzzy;
    /**
     * 购物车创建时间
     */
    public Date startTime;

    public String startTimeStart;

    public String startTimeEnd;

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

    public String getCartIdFuzzy() {
        return cartIdFuzzy;
    }

    public void setCartIdFuzzy(String cartIdFuzzy) {
        this.cartIdFuzzy = cartIdFuzzy;
    }

    public String getUserIdFuzzy() {
        return userIdFuzzy;
    }

    public void setUserIdFuzzy(String userIdFuzzy) {
        this.userIdFuzzy = userIdFuzzy;
    }

    public String getGodIdsFuzzy() {
        return godIdsFuzzy;
    }

    public void setGodIdsFuzzy(String godIdsFuzzy) {
        this.godIdsFuzzy = godIdsFuzzy;
    }

    public String getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(String startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public String getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(String startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

}