package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UserNameDuplicatedException;
import com.cy.store.service.ex.UserNotFoundException;
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


    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
