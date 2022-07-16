package com.cy.store.controller;

import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UserNameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 控制层基类
 *
 * @author: LYF
 * @date: 2022/7/16
 */
public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UserNameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("用户注册异常，请重试");
        }
        return result;
    }
}