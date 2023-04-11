package cn.edu.guet.weappdemo.bean;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/11 12:55
 */
public class OrderTrue {
   private Integer Order_id;
   private Integer  id;

    public Integer getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(Integer order_id) {
        Order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOrder_solve() {
        return order_solve;
    }

    public void setOrder_solve(String order_solve) {
        this.order_solve = order_solve;
    }

    public String getGeneration_time() {
        return Generation_time;
    }

    public void setGeneration_time(String generation_time) {
        Generation_time = generation_time;
    }

    private Integer  num;
    private String name;
   private String img;
   private String type;
   private double price;
   private Integer  nums;
   private Integer  user_id;
   private String out_trade_no;
   private String order_solve;
   private String Generation_time;



}
