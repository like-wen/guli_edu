package com.lkw.servicebase.exceptionhandler;



import com.lkw.cmsservice.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    //全局异常处理
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
    }



    //指定异常执行方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("方法执行ArithmeticException异常！");
    }

    //自定义的异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
