package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:29
 * version 1.0
 */
@Data
public class DocumentModel {

    @ExcelProperty(value = "Name", index = 0)
    private String name;

    @ExcelProperty(value = "CustomerId", index = 1)
    private Integer customerId;
}
