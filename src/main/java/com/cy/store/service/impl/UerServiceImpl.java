package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UserNameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
