package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
