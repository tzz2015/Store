package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
public interface IDistrictService {
    /**
     * 根据父代号查询区域信息
     *
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);

    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的名称，如果没有匹配的数据则返回null
     */
    String getNameByCode(String code);
}
