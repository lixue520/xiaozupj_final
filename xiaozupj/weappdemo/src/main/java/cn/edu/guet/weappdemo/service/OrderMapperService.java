package cn.edu.guet.weappdemo.service;

import cn.edu.guet.weappdemo.bean.HistoryOrder;
import cn.edu.guet.weappdemo.bean.OrderTrue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/11 13:55
 */
public interface OrderMapperService {

    //给支付用的购物车订单数据,以及交易号
    List<Map> Add_Order(Map<String,List<Map>> map,String str);

    //交易插入的是统一订单，只有一条数据
    void  Add_HistoryOrder(HistoryOrder historyOrder);

    String Select_Current_PayState(String  out_trade_no);

    List<OrderTrue> Select_All_Order();

    Map<String,String> ChangeState(Map<String,String> map);


}
