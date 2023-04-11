package cn.edu.guet.weappdemo.mapper;

import cn.edu.guet.weappdemo.bean.AddFoodBean;
import cn.edu.guet.weappdemo.bean.FoodContent;
import cn.edu.guet.weappdemo.bean.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Liwei
 * @Date 2021-08-13 17:50
 */
@Mapper
public interface FoodMapper {
    List<FoodContent> getFoodList();

    void addFood(AddFoodBean addFoodBean);

    void editFood(AddFoodBean addFoodBean);

    List<FoodContent> selectFoodByProductId(String product_id);

    List<FoodContent> selectFoodByProductName(String parameter);

    List<FoodContent> optionFood(String parameter);

    List<FoodContent> deleteFood(String parameter);
}
