package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author: LYF
 * @date: 2022/7/13
 */
@Service
public class UerServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void register(User user) {
        // 判断用户是否被注册过
        String username = user.getUsername();
        User result = userMapper.findByUserName(username);
        if (result != null) {
            throw new UserNameDuplicatedException("用户名已经被注册");
        }
        // 密码加密
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMD5Password(oldPassword, salt));

        // 数据补全
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        // 开始注册
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("注册异常了");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUserName(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 将用户密码按之前的加密规则获取加密后的密码
        String newPassword = getMD5Password(password, result.getSalt());
        String oldPassword = result.getPassword();
        // 比较
        if (!newPassword.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        // 判断is_delete 字段判断用户是否已经删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 原始密码和数据库密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!oldMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }
        // 设置新密码
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer row = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (row != 1) {
            throw new UpdateException("修改密码异常");
        }
    }


    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
