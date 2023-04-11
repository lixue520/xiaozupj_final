package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:31
 * version 1.0
 */
@Data
public class QuestionTooltipModel {

    @ExcelProperty(value = "ParentIndex", index = 0)
    private Integer parentIndex;

    @ExcelProperty(value = "Index", index = 1)
    private Integer index;

    @ExcelProperty(value = "Text", index = 2)
    private String text;

    @ExcelProperty(value = "StartIndex", index = 3)
    private Integer startIndex;

    @ExcelProperty(value = "EndIndex", index = 4)
    private Integer endIndex;
}
