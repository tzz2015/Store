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

    /**
     * 根据uid更新用户资料
     * @param user 封装了用户id和新个人资料的对象
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

}
