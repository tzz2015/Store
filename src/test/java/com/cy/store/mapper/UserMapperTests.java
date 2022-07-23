package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: LYF
 * @date: 2022/7/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("刘宇飞");
        user.setPassword("123456");
        Integer row = userMapper.insert(user);
        System.out.println("影响行数：" + row);
    }

    @Test
    public void findByUserName() {
        User user = userMapper.findByUserName("刘宇飞");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        Integer row = userMapper.updatePasswordByUid(8, "123456", "管理员", new Date());
        System.out.println("row:" + row);
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(8));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(4);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }


}
