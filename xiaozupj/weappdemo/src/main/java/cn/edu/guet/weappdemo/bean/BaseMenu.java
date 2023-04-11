package cn.edu.guet.weappdemo.bean;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/6 23:08
 */
public class BaseMenu {



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


    private String name;

    private String img;



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


}
