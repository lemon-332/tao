package com.tao.entity.query;

import java.util.Date;

public class SellerQuery extends BaseParam {
    /**
     * 商家Id
     */
    public String sellerId;

    public String sellerIdFuzzy;
    /**
     * 所有商品的id
     */
    public String godIds;

    public String godIdsFuzzy;
    /**
     * 商家地址
     */
    public String sellerAdress;

    public String sellerAdressFuzzy;
    /**
     * 商家图片
     */
    public String sellerImage;

    public String sellerImageFuzzy;
    /**
     * 商家的star个数
     */
    public Integer sellerStar;
    /**
     * 商家开业时间
     */
    public Date startTime;

    public String startTimeStart;

    public String startTimeEnd;
    /**
     * 商家闭业时间
     */
    public Date endTime;

    public String endTimeStart;

    public String endTimeEnd;

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

    public String getSellerIdFuzzy() {
        return sellerIdFuzzy;
    }

    public void setSellerIdFuzzy(String sellerIdFuzzy) {
        this.sellerIdFuzzy = sellerIdFuzzy;
    }

    public String getGodIdsFuzzy() {
        return godIdsFuzzy;
    }

    public void setGodIdsFuzzy(String godIdsFuzzy) {
        this.godIdsFuzzy = godIdsFuzzy;
    }

    public String getSellerAdressFuzzy() {
        return sellerAdressFuzzy;
    }

    public void setSellerAdressFuzzy(String sellerAdressFuzzy) {
        this.sellerAdressFuzzy = sellerAdressFuzzy;
    }

    public String getSellerImageFuzzy() {
        return sellerImageFuzzy;
    }

    public void setSellerImageFuzzy(String sellerImageFuzzy) {
        this.sellerImageFuzzy = sellerImageFuzzy;
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

    public String getEndTimeStart() {
        return endTimeStart;
    }

    public void setEndTimeStart(String endTimeStart) {
        this.endTimeStart = endTimeStart;
    }

    public String getEndTimeEnd() {
        return endTimeEnd;
    }

    public void setEndTimeEnd(String endTimeEnd) {
        this.endTimeEnd = endTimeEnd;
    }

}