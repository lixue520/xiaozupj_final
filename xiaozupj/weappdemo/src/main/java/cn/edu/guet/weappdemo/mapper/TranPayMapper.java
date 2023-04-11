package cn.edu.guet.weappdemo.mapper;

import cn.edu.guet.weappdemo.bean.OrderTrue;
import cn.edu.guet.weappdemo.bean.TranPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/14 15:37
 */
@Mapper
public interface TranPayMapper {
    void insertPay(TranPay tranPay);

    List<TranPay> findAll();

    void Update();

    void Dell(String out_trade_no);

    Map<Object,Object> FindPayImgByUserID(int id);

    Map<Object,Object> findOrderImg(String out_trade_no);

}
