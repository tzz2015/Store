package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/8/6
 */
public interface ProductMapper {

    List<Product> findHotList();

    Product findById(Integer id);
}
