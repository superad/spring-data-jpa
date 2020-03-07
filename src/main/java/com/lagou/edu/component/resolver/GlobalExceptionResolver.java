package com.lagou.edu.component.resolver;

import com.lagou.edu.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author fgm
 * @description  全局异常处理
 * @date 2020-03-05
 ***/
@ControllerAdvice
public class GlobalExceptionResolver {



    @ExceptionHandler(value = Exception.class)
    public <T>Result<T> resolveException(Exception ex){
        //todo
        ex.printStackTrace();
        return Result.failed(Result.SERVER_ERROR,"服务处理异常!");
    }


}
