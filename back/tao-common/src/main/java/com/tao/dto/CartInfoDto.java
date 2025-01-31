package com.tao.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CartInfoDto implements Serializable {
    private String cartId;
    private String userName;

    private int totalPrice;

    private int cartStatus;

    private Date startTime;

    private List<GodInfoDto> godInfoDtos;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(int cartStatus) {
        this.cartStatus = cartStatus;
    }

    public List<GodInfoDto> getGodInfoDtos() {
        return godInfoDtos;
    }

    public void setGodInfoDtos(List<GodInfoDto> godInfoDtos) {
        this.godInfoDtos = godInfoDtos;
    }
}
