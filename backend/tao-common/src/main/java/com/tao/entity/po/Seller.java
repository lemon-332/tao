package com.tao.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public class Seller implements Serializable {
    /**
     * 商家Id
     */
    public String sellerId;
    /**
     * 所有商品的id
     */
    public String godIds;
    /**
     * 商家地址
     */
    public String sellerAdress;
    /**
     * 商家图片
     */
    public String sellerImage;
    /**
     * 商家的star个数
     */
    public Integer sellerStar;
    /**
     * 商家开业时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startTime;
    /**
     * 商家闭业时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date endTime;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getGodIds() {
        return godIds;
    }

    public void setGodIds(String godIds) {
        this.godIds = godIds;
    }

    public String getSellerAdress() {
        return sellerAdress;
    }

    public void setSellerAdress(String sellerAdress) {
        this.sellerAdress = sellerAdress;
    }

    public String getSellerImage() {
        return sellerImage;
    }

    public void setSellerImage(String sellerImage) {
        this.sellerImage = sellerImage;
    }

    public Integer getSellerStar() {
        return sellerStar;
    }

    public void setSellerStar(Integer sellerStar) {
        this.sellerStar = sellerStar;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId = " + sellerId +
                ", godIds = " + godIds +
                ", sellerAdress = " + sellerAdress +
                ", sellerImage = " + sellerImage +
                ", sellerStar = " + sellerStar +
                ", startTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime) +
                ", endTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime) +
                '}';
    }
}