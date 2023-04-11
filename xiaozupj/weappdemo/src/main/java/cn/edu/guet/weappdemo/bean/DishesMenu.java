package cn.edu.guet.weappdemo.bean;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/10 15:27
 */
public class DishesMenu extends BaseMenu {
    private Integer id;
    private String type;
    private String name;
    private double price;
    private int nums;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

}
