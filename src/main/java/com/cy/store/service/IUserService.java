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


    /**
     * 获取当前登录的用户的信息
     *
     * @param uid 当前登录的用户的id
     * @return 当前登录的用户的信息
     */
    User getByUid(Integer uid);


    /**
     * 修改密码
     *
     * @param uid         当前登录的用户id
     * @param username    用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 修改用户资料
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param user 用户的新的数据
     */
    void changeInfo(Integer uid, String username, User user);
}
