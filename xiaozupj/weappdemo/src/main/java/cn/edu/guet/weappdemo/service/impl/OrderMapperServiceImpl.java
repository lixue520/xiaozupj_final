package cn.edu.guet.weappdemo.service.impl;

import cn.edu.guet.weappdemo.bean.HistoryOrder;
import cn.edu.guet.weappdemo.bean.OrderTrue;
import cn.edu.guet.weappdemo.mapper.OrderMapper;
import cn.edu.guet.weappdemo.service.OrderMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/11 13:56
 */
@Service //不标注控制器找不到
public class OrderMapperServiceImpl implements OrderMapperService {

    @Autowired
    OrderMapper orderMapper;


    @Override
    public List<Map> Add_Order(Map<String, List<Map>> map,String out_trade_no) {
        Map NewMap=null; //多个构造map作为List的子元素
        List<Map> NewList=new ArrayList<>();//最后以List<Map>返回给Mapper使用
        for(String k:map.keySet()){
            for(int i=0;i<map.get(k).size();i++){
                NewMap=new HashMap();
                NewMap = (Map) map.get(k).get(i).get("AtCard");
                NewMap.put("num",map.get(k).get(i).get("num"));
                NewMap.put("user_id",map.get(k).get(i).get("user_id"));
                NewMap.put("out_trade_no",out_trade_no);
                NewMap.put("order_solve","no"); //支付时改掉
                NewMap.put("Generation_time",map.get(k).get(i).get("Generation_time"));
                System.out.println("新的map诞生");
                System.out.println(NewMap);
                NewList.add(NewMap);
            }
        }
        //打印最终结果
        System.out.println(NewList);
        orderMapper.Add_Order(NewList);
        return NewList;
    }

    @Override
    public void Add_HistoryOrder(HistoryOrder historyOrder) {
        orderMapper.Add_Pay_Order(historyOrder);
        orderMapper.Update_Order_State(historyOrder.getOut_trade_no());
    }

    @Override
    public String Select_Current_PayState(String out_trade_no) {
        return orderMapper.Select_Current_PayState(out_trade_no);
    }

    @Override
    public List<OrderTrue> Select_All_Order() {
        return orderMapper.findAllOrder();
    }

    @Override
    public Map<String, String> ChangeState(Map<String,String> map) {
        //先默认调完成
        Map mapcheck = new HashMap();
        mapcheck.put("id",Integer.parseInt(map.get("id")));
        mapcheck.put("out_trade_no",map.get("out_trade_no"));
        mapcheck.put("order_solve",map.get("order_solve"));
        if(map.get("set")=="dell"){
            orderMapper.DellOrder(mapcheck);
        }else {
            orderMapper.ChangeState(mapcheck);
        }
        return mapcheck;
    }



}
