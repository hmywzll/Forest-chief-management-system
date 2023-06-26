package com.forestchiefmanagementsystem.config.exception;

import com.forestchiefmanagementsystem.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 异常处理器
 */
@RestControllerAdvice
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

////    处理重复账户名异常
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public R<String> ExceptionHandler(SQLIntegrityConstraintViolationException e){
//        if (e.getMessage().contains("Duplicate entry")) {
//            String username = e.getMessage().split(" ")[2];
//            log.info("账户名冲突 : " + e.toString());
//            return R.error(username+" 已存在");
//        }
//        return UnknownExceptionHandler(e);
//    }

////    自定义异常处理
//    @ExceptionHandler(CustomException.class)
//    public R<String> CustomExceptionHandler(CustomException e){
//        log.error(e.getStackTrace()[1].toString()+":\n"+e.toString());
//        return R.error(e.getMessage());
//    }

//    未知错误处理
    @ExceptionHandler(Exception.class)
    public R<String> UnknownExceptionHandler(Exception e){
//        log.error(e.getStackTrace()[1].toString()+":\n"+e.toString());
        e.printStackTrace();
        return R.error("未知错误");
    }


}
