package com.crush.excel.mapper;

import com.crush.excel.pojo.QuestionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-10-31 9:54
 * version 1.0
 */

public class QuestionMapper {

    public void save(List<QuestionModel> list){
        List<Integer> count=new ArrayList<Integer>();
        list.forEach(documentModel ->{
            Integer i=1;
            count.add(1);
            System.out.println(documentModel);
                }
        );
        System.out.println(count.size());
    }

}
