package com.crush.excel.mapper;

import com.crush.excel.pojo.DemoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-10-31 11:39
 * version 1.0
 */
@Repository
public class DemoMapper {

    public void save(List<DemoModel> demoModels){
        System.out.println("Mapper:"+demoModels);
    }
}
