package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> findByUid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void delete(Integer aid, Integer uid, String username);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);
}
