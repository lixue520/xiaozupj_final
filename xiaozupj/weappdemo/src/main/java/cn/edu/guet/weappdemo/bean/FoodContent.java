package cn.edu.guet.weappdemo.bean;

import java.util.Objects;

/**
 * @author Binqing
 * @类说明
 * @date 2022/8/9
 */
public class FoodContent {
    private String product_id;
    private String product_category;
    private String product_name;
    private String price;
    private String stock;
    private String img;

    public FoodContent() {
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg_url(String img_url) {
        this.img = img_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodContent that = (FoodContent) o;
        return Objects.equals(product_id, that.product_id) && Objects.equals(product_category, that.product_category) && Objects.equals(product_name, that.product_name) && Objects.equals(price, that.price) && Objects.equals(stock, that.stock) && Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_category, product_name, price, stock, img);
    }

    @Override
    public String toString() {
        return "FoodContent{" +
                "product_id='" + product_id + '\'' +
                ", product_category='" + product_category + '\'' +
                ", product_name='" + product_name + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                ", img_url='" + img + '\'' +
                '}';
    }
}
