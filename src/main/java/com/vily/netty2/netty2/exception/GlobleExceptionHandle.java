package com.vily.netty2.netty2.exception;

import com.vily.netty2.netty2.bean.ResultV;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  * description :   全局异常拦截器
 *  * Author : Vily
 *  * Date : 2019-06-25
 *  
 **/
@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandle {
    @ExceptionHandler({Exception.class})
    public ResultV exception(Exception ex) {

        ex.printStackTrace();
        ResultV resultV=new ResultV();
        resultV.setCode(1);
        resultV.setMessage("服务器繁忙，请稍后再试");
        return resultV;

    }
}
