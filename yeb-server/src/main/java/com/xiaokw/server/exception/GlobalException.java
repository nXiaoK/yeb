package com.xiaokw.server.exception;

import com.xiaokw.server.entity.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
//@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class) // 所有SQLException的异常都会捕获
    public AjaxResult mySqlException(SQLException e) {
        e.printStackTrace();
        if(e instanceof SQLIntegrityConstraintViolationException){
            return AjaxResult.error("该数据有关联数据，操作失败");
        }
        return AjaxResult.error("数据库异常，操作失败！");
    }
}
