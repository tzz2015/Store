package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: LYF
 * @date: 2022/7/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("小海豚");
            user.setPassword("123456");
            userService.register(user);
            System.out.println("操作成功");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void login() {
        User user = userService.login("liuyufei", "123456");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
      userService.changePassword(4,"管理员","123456","654321");
    }

    @Test
    public void getByUid() {
        try {
            Integer uid = 4;
            User user = userService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            Integer uid = 5;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
