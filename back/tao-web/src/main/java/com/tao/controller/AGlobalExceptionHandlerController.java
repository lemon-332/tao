package com.tao.controller;

import com.tao.entity.vo.ResponseVo;
import com.tao.exception.BusinessException;
import com.tao.myEnum.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@RestControllerAdvice
public class AGlobalExceptionHandlerController extends ABaseController {

    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request) {
        ResponseVo ajaxResponse = new ResponseVo();
        if (e instanceof NoHandlerFoundException) {
            ajaxResponse.setCode(ResponseCode.CODE_404.getCode());
            ajaxResponse.setInfo(ResponseCode.CODE_404.getInfo());
            ajaxResponse.setStatus(STATUS_ERROR);
        } else if (e instanceof BusinessException) {
            BusinessException biz = (BusinessException) e;
            ajaxResponse.setCode(biz.getCode());
            ajaxResponse.setInfo(biz.getMessage());
        } else if (e instanceof BindException) {
            ajaxResponse.setCode(ResponseCode.CODE_600.getCode());
            ajaxResponse.setInfo(ResponseCode.CODE_600.getInfo());
            ajaxResponse.setStatus(STATUS_ERROR);
        } else {
            ajaxResponse.setCode(ResponseCode.CODE_500.getCode());
            ajaxResponse.setInfo(ResponseCode.CODE_500.getInfo());
            ajaxResponse.setStatus(STATUS_ERROR);
        }

        return ajaxResponse;
    }
}
