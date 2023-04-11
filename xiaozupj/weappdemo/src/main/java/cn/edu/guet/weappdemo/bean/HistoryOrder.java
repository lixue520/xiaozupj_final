package cn.edu.guet.weappdemo.bean;

import java.sql.Timestamp;


public class HistoryOrder {

    private String uuid;
    private Integer mch_id; //商户id
    private String out_trade_no;
    private Timestamp order_time;
    private String transaction_id;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getMch_id() {
        return mch_id;
    }

    public void setMch_id(Integer mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }


}

