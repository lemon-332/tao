package com.tao.myEnum;

public enum UserStatus {
    DEACTIVE(0), ACTIVE(1), DELETE(2);


    Integer status;

    private UserStatus(int size) {
        this.status = size;
    }

    public Integer getUserStatus() {
        return this.status;
    }
}
