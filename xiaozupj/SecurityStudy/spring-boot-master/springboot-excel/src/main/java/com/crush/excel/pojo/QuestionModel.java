package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 9:42
 * version 1.0
 */
@Data
public class QuestionModel {

    /**
     * @ExcelProperty(index = 0)
     * 强制读取第0个
     * @ExcelProperty("字符串标题") 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     * <p>
     * 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(value = "SectionIndex", index = 0)
    private String sectionIndex;

    @ExcelProperty(value = "Index", index = 1)
    private String index;

    @ExcelProperty(value = "Question", index = 2)
    private String question;

    @ExcelProperty(value = "Order", index = 3)
    private String order;

    @ExcelProperty(value = "Required", index = 4)
    private String required;

    @ExcelProperty(value = "AnswerType", index = 5)
    private String answerType;

    @ExcelProperty(value = "AllowComments", index = 6)
    private String allowComments;

    @ExcelProperty(value = "EnhancedField", index = 7)
    private String enhancedField;

    @ExcelProperty(value = "ParentIndex", index = 8)
    private String parentIndex;
}
