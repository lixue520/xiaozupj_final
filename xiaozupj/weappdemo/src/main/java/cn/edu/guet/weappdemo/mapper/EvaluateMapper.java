package cn.edu.guet.weappdemo.mapper;

import cn.edu.guet.weappdemo.bean.Evaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EvaluateMapper {
    List<Evaluate> findEvaluate();
    List<Evaluate> find1Evaluate(String user_name);
    List<Evaluate> find2Evaluate();
    void deleteEvaluate(int id);
    void insertEvaluate(int id,String user_name,String item_name,String time,String evaluate);
}