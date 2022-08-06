package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface AddressMapper {
    /**
     * 插入用户收获地址
     *
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 用户的收获地址数量
     *
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 根据uid 查询用户的收货地址
     *
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);
}
