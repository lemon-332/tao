package com.tao.exception;

import com.tao.myEnum.ResponseCode;
public class BusinessException extends RuntimeException {
    private ResponseCode codeEnum;
    private Integer code;
    private String message;

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(ResponseCode codeEnum) {
        super(codeEnum.getInfo());
        this.codeEnum = codeEnum;
        this.code = codeEnum.getCode();
        this.message = codeEnum.getInfo();
    }
    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ResponseCode getCodeEnum() {
        return codeEnum;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 业务异常不需要堆栈信息 提高效率
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
