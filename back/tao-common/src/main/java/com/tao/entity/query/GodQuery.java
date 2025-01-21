package com.tao.entity.query;

import java.util.Date;

public class GodQuery extends BaseParam {
    /**
     * 商品Id
     */
    public String godId;

    public String godIdFuzzy;
    /**
     * 商品名称
     */
    public String godName;

    public String godNameFuzzy;
    /**
     * 商品图片
     */
    public String godImage;

    public String godImageFuzzy;
    /**
     * 商品描述
     */
    public String godDesc;

    public String godDescFuzzy;
    /**
     * 商品总数
     */
    public Integer godCount;
    /**
     * 已卖商品数量
     */
    public Integer godBoughtCount;
    /**
     * 商品star个数
     */
    public Integer godStar;
    /**
     * 商家id
     */
    public String sellerId;

    public String sellerIdFuzzy;
    /**
     * 所有的评论Id
     */
    public String commentIds;

    public String commentIdsFuzzy;
    /**
     * 商品上架时间
     */
    public Date startTime;

    public String startTimeStart;

    public String startTimeEnd;

    public String getGodId() {
        return godId;
    }

    public void setGodId(String godId) {
        this.godId = godId;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public String getGodImage() {
        return godImage;
    }

    public void setGodImage(String godImage) {
        this.godImage = godImage;
    }

    public String getGodDesc() {
        return godDesc;
    }

    public void setGodDesc(String godDesc) {
        this.godDesc = godDesc;
    }

    public Integer getGodCount() {
        return godCount;
    }

    public void setGodCount(Integer godCount) {
        this.godCount = godCount;
    }

    public Integer getGodBoughtCount() {
        return godBoughtCount;
    }

    public void setGodBoughtCount(Integer godBoughtCount) {
        this.godBoughtCount = godBoughtCount;
    }

    public Integer getGodStar() {
        return godStar;
    }

    public void setGodStar(Integer godStar) {
        this.godStar = godStar;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(String commentIds) {
        this.commentIds = commentIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getGodIdFuzzy() {
        return godIdFuzzy;
    }

    public void setGodIdFuzzy(String godIdFuzzy) {
        this.godIdFuzzy = godIdFuzzy;
    }

    public String getGodNameFuzzy() {
        return godNameFuzzy;
    }

    public void setGodNameFuzzy(String godNameFuzzy) {
        this.godNameFuzzy = godNameFuzzy;
    }

    public String getGodImageFuzzy() {
        return godImageFuzzy;
    }

    public void setGodImageFuzzy(String godImageFuzzy) {
        this.godImageFuzzy = godImageFuzzy;
    }

    public String getGodDescFuzzy() {
        return godDescFuzzy;
    }

    public void setGodDescFuzzy(String godDescFuzzy) {
        this.godDescFuzzy = godDescFuzzy;
    }

    public String getSellerIdFuzzy() {
        return sellerIdFuzzy;
    }

    public void setSellerIdFuzzy(String sellerIdFuzzy) {
        this.sellerIdFuzzy = sellerIdFuzzy;
    }

    public String getCommentIdsFuzzy() {
        return commentIdsFuzzy;
    }

    public void setCommentIdsFuzzy(String commentIdsFuzzy) {
        this.commentIdsFuzzy = commentIdsFuzzy;
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