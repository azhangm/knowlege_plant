package com.dajuancai.knowledge_plant.controller;


import com.dajuancai.knowledge_plant.commen.ApiResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/*
* 校验 异常 统一处理
*
* */

public class CollerExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public ApiResponse vaildExceptionHandler (BindException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return apiResponse;
    }
}
