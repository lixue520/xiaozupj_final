package cn.edu.guet.weappdemo.mapper;
import cn.edu.guet.weappdemo.bean.BaseMenu;
import cn.edu.guet.weappdemo.bean.DishesMenu;
import cn.edu.guet.weappdemo.bean.MySwiperLeft;
import cn.edu.guet.weappdemo.bean.OrderTrue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/6 15:40
 */
@Mapper  //告诉springboot这是一个mybits的mapper
@Repository //将这个MenuMapper交给spring容器进行管理
public interface MenuMapper {
     //测试接口
     List<MySwiperLeft> findAllMenu();
     List<BaseMenu> find_All_Menu_Content();

     //实际接口
     List<DishesMenu> findAll();
     //插入一个数据:类型为BaseMenu
     void Add_Img(BaseMenu baseMenu);
     List<DishesMenu> Select_Dishes_Menu();
     //批量插入数据：类型为List
     ArrayList Add_Images(List<BaseMenu> list);

}
