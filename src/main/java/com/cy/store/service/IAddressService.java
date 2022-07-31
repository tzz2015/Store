package com.cy.store.service;

import com.cy.store.entity.Address;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
}
