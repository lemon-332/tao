package com.tao.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GodInfoDto implements Serializable {
    private String godId;
    private String godName;
    private BigDecimal godPrice;
    private int godCount;

    private String godImg;

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

    public BigDecimal getGodPrice() {
        return godPrice;
    }

    public void setGodPrice(BigDecimal godPrice) {
        this.godPrice = godPrice;
    }

    public int getGodCount() {
        return godCount;
    }

    public void setGodCount(int godCount) {
        this.godCount = godCount;
    }

    public String getGodImg() {
        return godImg;
    }

    public void setGodImg(String godImg) {
        this.godImg = godImg;
    }


}
