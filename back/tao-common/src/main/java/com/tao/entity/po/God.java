package com.tao.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lemon
 * @date 2025-01-31 17:59
 * @desc
 */
public class God implements Serializable {
    /**
     * 商品Id
     */
    public String godId;
    /**
     * 商品名称
     */
    public String godName;
    /**
     * 商品图片
     */
    public String godImage;
    /**
     * 商品描述
     */
    public String godDesc;
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
    /**
     * 所有的评论Id
     */
    public String commentIds;
    /**
     * 商品上架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startTime;
    /**
     * 商品价格
     */
    public BigDecimal godPrice;

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

    public BigDecimal getGodPrice() {
        return godPrice;
    }

    public void setGodPrice(BigDecimal godPrice) {
        this.godPrice = godPrice;
    }

    @Override
    public String toString() {
        return "God{" +
                "godId = " + godId +
                ", godName = " + godName +
                ", godImage = " + godImage +
                ", godDesc = " + godDesc +
                ", godCount = " + godCount +
                ", godBoughtCount = " + godBoughtCount +
                ", godStar = " + godStar +
                ", sellerId = " + sellerId +
                ", commentIds = " + commentIds +
                ", startTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime) +
                ", godPrice = " + godPrice +
                '}';
    }
}