package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:30
 * version 1.0
 */
@Data
public class SectionModel {

    @ExcelProperty(value = "Index", index = 0)
    private Integer index;

    @ExcelProperty(value = "Name", index = 1)
    private String name;

    @ExcelProperty(value = "Order", index = 2)
    private Integer order;

}
