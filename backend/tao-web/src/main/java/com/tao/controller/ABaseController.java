package com.tao.controller;

import com.tao.myEnum.ResponseCode;
import com.tao.entity.vo.ResponseVo;

import com.tao.exception.BusinessException;
public class ABaseController {
    protected static final String STATUS_SUCCESS="success";
    protected static final String STATUS_ERROR="error";

    protected <T> ResponseVo<T> getSuccessResponseVo(T t){
        ResponseVo<T> responseVo = new ResponseVo<>();
        responseVo.setStatus(STATUS_SUCCESS);
        responseVo.setCode(ResponseCode.CODE_200.getCode());
        responseVo.setInfo(ResponseCode.CODE_200.getInfo());
        responseVo.setData(t);
        return responseVo;
    }
}
