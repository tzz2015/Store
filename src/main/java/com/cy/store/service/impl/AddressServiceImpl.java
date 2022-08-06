package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @描述：
 * @author: LYF
 * @date: 2022/7/31
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收获地址超出上限");
        }
        // 补全数据：省、市、区的名称
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);
        // 补全日记
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        // 插入收获地址
        Integer row = addressMapper.insert(address);
        if (row != 1) {
            throw new InsertException("插入收获地址异常");
        }
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> addressList = addressMapper.findByUid(uid);
        for(Address address:addressList){
             address.setUid(null);
             address.setAid(null);
             address.setProvinceCode(null);
             address.setCityCode(null);
             address.setAreaCode(null);
             address.setZip(null);
             address.setModifiedTime(null);
             address.setModifiedUser(null);
             address.setCreatedTime(null);
             address.setCreatedUser(null);
             address.setTel(null);
        }
        return addressList;
    }
}
