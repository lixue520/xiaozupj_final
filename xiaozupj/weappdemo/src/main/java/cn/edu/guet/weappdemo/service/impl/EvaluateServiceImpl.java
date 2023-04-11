package cn.edu.guet.weappdemo.service.impl;

import cn.edu.guet.weappdemo.bean.Evaluate;
import cn.edu.guet.weappdemo.mapper.EvaluateMapper;
import cn.edu.guet.weappdemo.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public List<Evaluate> findEvaluate() {
        return evaluateMapper.findEvaluate();
    }
    @Override
    public List<Evaluate> find2Evaluate() {
        return evaluateMapper.find2Evaluate();
    }

    @Override
    public List<Evaluate> find1Evaluate(String user_name) {
        return evaluateMapper.find1Evaluate( user_name);
    }


    @Override
    public int deleteEvaluate(int id) {
        evaluateMapper.deleteEvaluate(id);
        return 1;
    }

    @Override
    public int insertEvaluate(int id,String user_name, String item_name, String time, String evaluate) {
        evaluateMapper.insertEvaluate(id,user_name,item_name,time,evaluate);
        return 0;
    }



}


