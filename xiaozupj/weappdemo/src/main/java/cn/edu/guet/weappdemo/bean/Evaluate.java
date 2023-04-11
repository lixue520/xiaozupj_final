package cn.edu.guet.weappdemo.bean;

import java.util.Objects;

public class Evaluate extends BaseModel{
    private Long id;
    private String time;
    private String evaluate;
    private String user_name;
    private String item_name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", user_name='" + user_name + '\'' +
                ", item_name='" + item_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluate evaluate1 = (Evaluate) o;
        return Objects.equals(id, evaluate1.id) && Objects.equals(time, evaluate1.time) && Objects.equals(evaluate, evaluate1.evaluate) && Objects.equals(user_name, evaluate1.user_name) && Objects.equals(item_name, evaluate1.item_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, evaluate, user_name, item_name);
    }
}
