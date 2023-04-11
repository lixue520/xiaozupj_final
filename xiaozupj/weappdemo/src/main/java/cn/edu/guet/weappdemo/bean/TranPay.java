package cn.edu.guet.weappdemo.bean;

import java.sql.Timestamp;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/14 15:34
 */
public class TranPay {

    private Integer id;
    private String out_trade_no;
    private Timestamp simple_time;
    private String pay_img;
    private String payment_method;

    public String getOrder_solve() {
        return order_solve;
    }

    public void setOrder_solve(String order_solve) {
        this.order_solve = order_solve;
    }

    private String order_solve;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Timestamp getSimple_time() {
        return simple_time;
    }

    public void setSimple_time(Timestamp simple_time) {
        this.simple_time = simple_time;
    }

    public String getPay_img() {
        return pay_img;
    }

    public void setPay_img(String pay_img) {
        this.pay_img = pay_img;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
