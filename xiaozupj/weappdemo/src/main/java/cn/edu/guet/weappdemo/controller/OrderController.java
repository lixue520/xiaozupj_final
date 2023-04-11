package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.bean.BaseMenu;
import cn.edu.guet.weappdemo.bean.DishesMenu;
import cn.edu.guet.weappdemo.bean.TranPay;
import cn.edu.guet.weappdemo.mapper.TranPayMapper;
import cn.edu.guet.weappdemo.pay.WXPay;
import cn.edu.guet.weappdemo.service.MenuMapperService;

import cn.edu.guet.weappdemo.service.OrderMapperService;
import cn.edu.guet.weappdemo.util.AutoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/3 13:29
 */
@RestController
@ResponseBody
@RequestMapping ("/wxApi")
public class OrderController {
    @Autowired
    private MenuMapperService menuMapperService;
    @Autowired
    private OrderMapperService orderMapperService;
    @Autowired
    private TranPayMapper tranPayMapper;


    @RequestMapping(value = "receive" ,
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Map<String,String> ReceiveOrder(@RequestBody Map<String,List<Map>> map) throws Exception {
        System.out.println("执行支付调度");
        //调用支付环节
        return WxPay(map);
    }

    //提供给接收器调度微信支付
    public Map<String,String> WxPay(Map<String,List<Map>> map) throws Exception {
        WXPay wxPay=new WXPay();
        String out_trade_no=wxPay.TwoCodePay(1);
        orderMapperService.Add_Order(map,out_trade_no);
        //更新服务器二维码
        new AutoTools().Post_Local_Jpg(out_trade_no); //将生成的二维码更新到服务器
        Map<String,String> callback=new HashMap<>();
        callback.put("out_trade_no",out_trade_no); //返回给小程序使用
        TranPay tranPay=new TranPay();
        tranPay.setOut_trade_no(out_trade_no);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        tranPay.setSimple_time(timestamp);
        tranPay.setPay_img(out_trade_no+".jpg");
        tranPay.setPayment_method("weCat");
        tranPayMapper.insertPay(tranPay);
        return callback;
    }



    @RequestMapping("callback")
    public void CallBackAndInsert(){
    // 依据回调信息将当前数据插入数据库
    }


    //用于前端进行批量插入请求
    @RequestMapping("insert")
    public List<BaseMenu> Bulk_Insert(){
        return  menuMapperService.Add_Images();
    }


    /**
     * @description: 微信前端与后端通信测试
     * @param : void
     * @return java.util.Map<java.lang.String,java.util.List>
     * @author: DavidNan
     * @date: 2022/8/9 20:52
     */
    //测试，实际不要用
    @RequestMapping ("/FindTest")
    public Map<String, List> SwiperMenuApiTest() {
        System.out.println("Get the sliding menu");
        return menuMapperService.findAllMenu();
    }

    @RequestMapping("/test")
    public BaseMenu Test(){
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.setId(1);
        baseMenu.setName("陈浩南");
        baseMenu.setImg("JPG0.jpg");
        menuMapperService.Add_Img(baseMenu);
        return baseMenu;
    }

    //菜单主要内容
    @RequestMapping ("/CaiDan")
    public List<DishesMenu> Confirm_Order(){
        System.out.println("Confirm the order confirmation request sent by the WeChat applet");
        return menuMapperService.findAll();
    }

    @RequestMapping("/test2")
    public List<DishesMenu> Test2(){
        return menuMapperService.Select_Dishes_Menu();
    }


    
}
