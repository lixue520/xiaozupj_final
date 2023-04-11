package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @Author: crush
 * @Date: 2021-10-31 11:27
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class DemoModel {

    @ExcelProperty(value = "博客名", index = 0)
    private String name;

    @ExcelProperty(value = "社区", index = 1)
    private String communityName;

    @ExcelProperty(value = "主页", index = 2)
    private String homePageUrl;

    @ExcelProperty(value = "涉及领域", index = 3)
    private String specialty;

    @ExcelProperty(value = "联系邮箱", index = 4)
    private String email;

    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "发布的第一篇原创文章", index = 5)
    private String startDate;
}
