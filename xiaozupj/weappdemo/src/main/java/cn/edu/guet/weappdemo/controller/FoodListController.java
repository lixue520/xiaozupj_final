package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.bean.AddFoodBean;
import cn.edu.guet.weappdemo.http.HttpResult;
import cn.edu.guet.weappdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Binqing
 * @类说明
 * @date 2022/8/4
 */
@RestController
@RequestMapping("food")
public class FoodListController {
    @Autowired
    private FoodService foodService;

    @GetMapping(value = "/foodList")
    public HttpResult getFoodList(@RequestParam String abc) {
        System.out.println("名称"+abc);
        System.out.println("准备获取菜品item");

        return HttpResult.ok(foodService.getFoodList());
    }

    @PostMapping(value = "/addFood")
    public HttpResult addFood(@RequestBody AddFoodBean addFoodBean) {
        System.out.println("----->添加新品"+addFoodBean.getProduct_id());
        //String product_id = addFoodBean.getProduct_id();
        //String product_category = addFoodBean.getProduct_category();
        //String product_name = addFoodBean.getProduct_name();
        //String price = addFoodBean.getPrice();
        //System.out.println(product_category);
        //System.out.println(product_name);
        //System.out.println(price);
        foodService.addFood(addFoodBean);
        return HttpResult.ok(addFoodBean);
    }
    @PostMapping(value = "/editFood")
    public HttpResult editFood(@RequestBody AddFoodBean addFoodBean) {
        System.out.println("----->修改餐品"+addFoodBean.getProduct_id());
        foodService.editFood(addFoodBean);
        return HttpResult.ok(addFoodBean);
    }

    @GetMapping(value = "/selectFood")
    public HttpResult selectFoodByProductId(@RequestParam String parameter) {
        System.out.println("----->搜索餐品"+parameter);
        if (parameter.contains("100")) {
            return HttpResult.ok(foodService.selectFoodByProductId(parameter));

        } else {
            return HttpResult.ok(foodService.selectFoodByProductName(parameter));
        }
        //System.out.println(parameter.contains("100"));
        //foodService.selectFoodByProductId(product_id);
        //return HttpResult.ok(foodService.selectFoodByProductId(parameter));
    }

    @GetMapping(value = "/optionFood")
    public HttpResult optionFood(@RequestParam String parameter) {

        return HttpResult.ok(foodService.optionFood(parameter));

    }

    @GetMapping(value = "/deleteFood")
    public HttpResult deleteFood(@RequestParam String parameter) {
        System.out.println("----->删除餐品"+parameter);
        return HttpResult.ok(foodService.deleteFood(parameter));
    }
}

