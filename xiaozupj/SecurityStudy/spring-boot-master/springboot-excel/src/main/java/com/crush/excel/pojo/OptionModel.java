package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:31
 * version 1.0
 */
@Data
public class OptionModel {

    @ExcelProperty(value = "QuestionIndex", index = 0)
    private Integer questionIndex;

    @ExcelProperty(value = "Index", index = 1)
    private Integer index;

    @ExcelProperty(value = "Value", index = 2)
    private String value;

    @ExcelProperty(value = "order", index = 3)
    private Integer order;
}
