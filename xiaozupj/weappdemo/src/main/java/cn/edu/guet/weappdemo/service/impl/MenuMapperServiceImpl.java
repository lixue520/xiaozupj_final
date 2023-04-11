package cn.edu.guet.weappdemo.service.impl;

import cn.edu.guet.weappdemo.bean.BaseMenu;
import cn.edu.guet.weappdemo.bean.DishesMenu;
import cn.edu.guet.weappdemo.mapper.MenuMapper;
import cn.edu.guet.weappdemo.service.MenuMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/6 15:41
 */
@Service
public class MenuMapperServiceImpl implements MenuMapperService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Map<String,List> findAllMenu() {
        Map map= new HashMap();
        map.put("left",menuMapper.find_All_Menu_Content());
        map.put("right",menuMapper.findAllMenu());
        return map;
    }

    @Override
    public List<DishesMenu> findAll() {
//        Map map = new HashMap<>();
//        map.put("AtCard",menuMapper.findAll());

        return menuMapper.findAll();
    }

    @Override
    public void Add_Img(BaseMenu baseMenu) {
        System.out.println("---------正在插入--------");
        menuMapper.Add_Img(baseMenu);
        System.out.println("---------插入成功--------");
    }

    @Override
    public List<BaseMenu> Add_Images() {   //不可更改findAll()，避免操作互相依赖的死锁，一个出错，两个都错
        List<BaseMenu>  list = new ArrayList<>();
        List<DishesMenu> MenuList =  menuMapper.Select_Dishes_Menu(); //从数据库获数据
        BaseMenu baseMenu = null;
//        正确的做法是：在循环的外部进行对象的声明，循环的里边进行创建，
//        这样内存中存储的只有一份对象的引用，
//        每次创建的时候只是引用指向不同的对象罢了，大大节省内存。
        for (DishesMenu item:MenuList) {
            baseMenu=new BaseMenu();
            baseMenu.setId(item.getId());  //一般设置为null,让数据库自动生成id,首次无值时使用id
            baseMenu.setName(item.getName());
            baseMenu.setImg("JPG"+(item.getId()-1)+".jpg");
            list.add(baseMenu);
        }
        menuMapper.Add_Images(list); //插入数据库，暂时默认当前数据库内容，后期要进行数据有值判断
        return list;
    }

    @Override
    public List<BaseMenu> find_All_Menu_Content() {
        return menuMapper.find_All_Menu_Content();
    }

    @Override
    public List<DishesMenu> Select_Dishes_Menu() {
        return menuMapper.Select_Dishes_Menu();
    }


}
