package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.http.HttpResult;
import cn.edu.guet.weappdemo.service.OrderMapperService;
import cn.edu.guet.weappdemo.service.TranPayMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/13 20:07
 */
@RestController
@RequestMapping("OrderManager")
public class SysOrderController {

    @Autowired
    OrderMapperService orderMapperService;
    @Autowired
    TranPayMapperService tranPayMapperService;

    @GetMapping("/please")
    public String Please(){
        return "用户申请";
    }

    @GetMapping("/user")
    public String UserAccess(){
        return "用户访问";
    }

    @GetMapping("/Order")
    public String OrderState(){
        return "订单状态";
    }

    @PostMapping(value = "/FinishOrder")
    public HttpResult FinishOrder(@RequestBody Map<String,String> map ) {
        //该接口接收订单号与商品ID
        System.out.println("该接口接收订单号与商品ID");
        System.out.println(map);
        //查询当前订单状态若为空或者ok等返回
        return HttpResult.ok(orderMapperService.ChangeState(map));
    }
    @GetMapping(value = "/PayFindAll")
    public HttpResult PayFindAll() {
        System.out.println("名称");
        System.out.println("准备获取菜品item");
        return HttpResult.ok(tranPayMapperService.FindAll());
    }

    @PostMapping(value = "/auditOrder")
    public HttpResult auditOrder(@RequestBody Map<String,String> map) {
        System.out.println("该接口接收订单号与商品ID");
        System.out.println(map);
        //查询当前订单状态若为空或者ok等返回
        return HttpResult.ok(orderMapperService.ChangeState(map));
    }

    @PostMapping(value = "/DellOrder")
    public HttpResult DellOrder(@RequestBody Map<String,String> map) {
        System.out.println("该接口接收订单号与商品ID");
        System.out.println(map);
        //查询当前订单状态若为空或者ok等返回
        return HttpResult.ok(orderMapperService.ChangeState(map));
    }

    @GetMapping(value = "/AllOrder")
    public HttpResult getFoodList() {
        System.out.println("名称");
        System.out.println("准备获取菜品item");
        return HttpResult.ok(orderMapperService.Select_All_Order());
    }

}
