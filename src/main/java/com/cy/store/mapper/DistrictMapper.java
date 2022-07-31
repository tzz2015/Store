package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface DistrictMapper {
    /**
     * 根据父代码查询区域
     *
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的名称，如果没有匹配的数据则返回null
     */
    String findNameByCode(String code);
}
