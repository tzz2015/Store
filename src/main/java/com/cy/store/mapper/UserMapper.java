package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

/**
 * @author: LYF
 * @date: 2022/7/11
 */
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    User findByUserName(String username);

    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    User findByUid(Integer uid);

}
