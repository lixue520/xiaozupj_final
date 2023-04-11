package cn.edu.guet.weappdemo.service;

import cn.edu.guet.weappdemo.bean.AddFoodBean;
import cn.edu.guet.weappdemo.bean.FoodContent;


import java.util.List;

public interface FoodService {
    List<FoodContent> getFoodList();

    void addFood(AddFoodBean addFoodBean);

    void editFood(AddFoodBean addFoodBean);


    List<FoodContent> selectFoodByProductId(String product_id);

    List<FoodContent> selectFoodByProductName(String parameter);

    List<FoodContent> optionFood(String parameter);

    List<FoodContent> deleteFood(String parameter);
}
