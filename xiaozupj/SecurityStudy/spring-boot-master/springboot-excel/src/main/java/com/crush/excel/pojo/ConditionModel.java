package com.crush.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: crush
 * @Date: 2021-10-31 12:31
 * version 1.0
 */
@Data
public class ConditionModel {

    @ExcelProperty(value = "QuestionIndex", index = 0)
    private Integer questionIndex;

    @ExcelProperty(value = "index", index = 1)
    private Integer  index;

    @ExcelProperty(value = "SelectedAnswer", index =2)
    private String selectedAnswer;

    @ExcelProperty(value = "Action", index = 3)
    private Integer action;

    @ExcelProperty(value = "TriggerQuestionIndex", index = 4)
    private Integer triggerQuestionIndex;

    @ExcelProperty(value = "TriggerSectionIndex", index = 5)
    private Integer triggerSectionIndex;

}
