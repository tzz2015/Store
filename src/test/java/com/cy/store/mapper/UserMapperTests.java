package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import javax.jws.soap.SOAPBinding;

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
    public void findByUserName(){
        User user = userMapper.findByUserName("刘宇飞");
        System.out.println(user);
    }

}
