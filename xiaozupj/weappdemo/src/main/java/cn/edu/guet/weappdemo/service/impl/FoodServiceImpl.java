package cn.edu.guet.weappdemo.service.impl;

import cn.edu.guet.weappdemo.bean.AddFoodBean;
import cn.edu.guet.weappdemo.bean.FoodContent;
import cn.edu.guet.weappdemo.bean.SysMenu;
import cn.edu.guet.weappdemo.mapper.FoodMapper;
import cn.edu.guet.weappdemo.mapper.SysMenuMapper;
import cn.edu.guet.weappdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Binqing
 * @类说明
 * @date 2022/8/9
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<FoodContent> getFoodList() {
        return  foodMapper.getFoodList();
    }

    @Override
    public void addFood(AddFoodBean addFoodBean) {
        foodMapper.addFood(addFoodBean);
    }

    @Override
    public void editFood(AddFoodBean addFoodBean) {
        foodMapper.editFood(addFoodBean);
    }

    @Override
    public List<FoodContent> selectFoodByProductId(String product_id) {

        return foodMapper.selectFoodByProductId( product_id);
    }

    @Override
    public List<FoodContent> selectFoodByProductName(String parameter) {
        return foodMapper.selectFoodByProductName( parameter);
    }

    @Override
    public List<FoodContent> optionFood(String parameter) {
        return foodMapper.optionFood( parameter);
    }

    @Override
    public List<FoodContent> deleteFood(String parameter) {
        return foodMapper.deleteFood( parameter);
    }


}
