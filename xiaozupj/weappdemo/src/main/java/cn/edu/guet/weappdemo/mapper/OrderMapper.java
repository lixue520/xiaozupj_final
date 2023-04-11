package cn.edu.guet.weappdemo.mapper;

import cn.edu.guet.weappdemo.bean.HistoryOrder;
import cn.edu.guet.weappdemo.bean.OrderTrue;
import cn.edu.guet.weappdemo.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/11 13:04
 */

@Mapper  //告诉springboot这是一个mybits的mapper
@Repository //将这个MenuMapper交给spring容器进行管理
public interface OrderMapper {

    //插入前端发过来订单的数据
    void Add_Order(List<Map> list);  //为了方便先经过处理形成新的List<map>,再传进来

    //查询当前订单数据
    List Select_Now_Order();

    //支付生成的统一订单数据插入
    void Add_Pay_Order(HistoryOrder historyOrder);


    //查询历史订单数据
    void Select_History_Order();

    //根据当前订单号查找订单号下的订单，并将订单下的商品状态更新为已处理
    void Update_Order_State(@Param("out_trade_no") String out_trade_no);

   //查询当前订单下的支付状态:前端下单后成功，回调函数返回out_trade_no，回调函数中调用
   // 获取状态函数，并将此参数传入，转发到后台服务接口，调用mapper返回数据给回调函数，并刷新订单状态
    //订单支付后，刷新前端的订单状态=null，即当前没有下单；
   String Select_Current_PayState(@Param("out_trade_no") String out_trade_no);

    List<OrderTrue> findAllOrder();

    //订单状态管理
    void ChangeState(Map map);

    void DellOrder(Map map);
}
