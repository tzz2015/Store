package com.cy.store.mapper;

import com.cy.store.entity.Address;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface AddressMapper {
    /**
     * 插入用户收获地址
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 用户的收获地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);
}
