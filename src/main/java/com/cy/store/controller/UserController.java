package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: LYF
 * @date: 2022/7/16
 */
//@Controller
@RestController // controller + responseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("register")
    public JsonResult<Void> register(User user) {
        userService.register(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        User data = userService.login(username, password);
        //登录成功后，将uid和username存入到HttpSession中
        httpSession.setAttribute("uid", data.getUid());
        httpSession.setAttribute("username", data.getUsername());
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象执行修改密码
        userService.changePassword(uid, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<>(OK);
    }
}
