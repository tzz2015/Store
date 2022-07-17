package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * @author: LYF
 * @date: 2022/7/13
 */
public interface IUserService {
    /**
     * 注册用户
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户登录
     */
    User login(String username, String password);


}
