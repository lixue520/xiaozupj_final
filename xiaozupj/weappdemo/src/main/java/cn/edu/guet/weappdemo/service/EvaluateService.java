package cn.edu.guet.weappdemo.service;

import cn.edu.guet.weappdemo.bean.Evaluate;

import java.util.List;

public interface EvaluateService {
    List<Evaluate> findEvaluate();
    List<Evaluate> find2Evaluate();
    int deleteEvaluate(int id);
    int insertEvaluate(int id,String user_name,String item_name,String time,String evaluate);

    List<Evaluate> find1Evaluate(String user_name);
}
