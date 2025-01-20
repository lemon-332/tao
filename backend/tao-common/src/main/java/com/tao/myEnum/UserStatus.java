package com.tao.myEnum;

public enum UserStatus {
    ACTIVE(1), DEACTIVE(2), DELETE(3);


    Integer status;

    private UserStatus(int size) {
        this.status = size;
    }

    public Integer getUserStatus() {
        return this.status;
    }
}
