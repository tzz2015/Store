package com.cy.store.service;

import com.cy.store.entity.Address;
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
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(4);
        address.setName("我老公");
        address.setPhone("1785802974");
        address.setAddress("雁塔区小寨赛格");
        addressService.addNewAddress(4, "管理员", address);
    }
}
