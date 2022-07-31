package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: LYF
 * @date: 2022/7/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("210100");
        for (District district : list) {
            System.out.println(district);
        }
    }
}
