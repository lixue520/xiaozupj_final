package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:30
 * version 1.0
 */
@Data
public class TemplateModel {

    @ExcelProperty(value = "Name", index = 0)
    private String name;

    @ExcelProperty(value = "DocumentType", index = 1)
    private Integer documentType;

    @ExcelProperty(value = "LobId", index = 2)
    private Integer lobId;

    @ExcelProperty(value = "UserGroupId", index = 3)
    private Integer userGroupId;

    @ExcelProperty(value = "FontStyleId", index = 4)
    private Integer fontStyleId;
}
