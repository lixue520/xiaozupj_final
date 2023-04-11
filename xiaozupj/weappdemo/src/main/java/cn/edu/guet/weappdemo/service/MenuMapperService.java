package cn.edu.guet.weappdemo.service;

import cn.edu.guet.weappdemo.bean.BaseMenu;
import cn.edu.guet.weappdemo.bean.DishesMenu;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/6 15:41
 */
public interface MenuMapperService {
   Map<String,List> findAllMenu();
   List<DishesMenu> findAll();
   void Add_Img(BaseMenu baseMenu);
   List<BaseMenu> Add_Images();
   List<BaseMenu> find_All_Menu_Content();
   List<DishesMenu> Select_Dishes_Menu();
}
