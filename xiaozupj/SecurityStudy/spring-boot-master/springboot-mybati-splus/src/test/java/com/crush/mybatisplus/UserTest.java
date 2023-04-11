package com.crush.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crush.mybatisplus.entity.User;
import com.crush.mybatisplus.mapper.UserMapper;
import com.crush.mybatisplus.service.IUserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: crush
 * @Date: 2021-07-23 14:41
 * version 1.0
 */
@SpringBootTest
public class UserTest {


    @Autowired
    IUserService tbUserService;

    @Autowired
    UserMapper userMapper;

    /**
     * 事务管理
     */
    @Transactional
    @Test
    public void testInsert2(){
        User crush = new User().setUsername("宁在春test").setPasswrod("987456");
        userMapper.insert(crush);
    }


    /**
     * 事务管理
     */
    @Transactional
    @Test
    public void testInsert(){
        User crush = new User().setUsername("宁在春").setPasswrod("987456");
        boolean b = tbUserService.save(crush);
        System.out.println(b);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("ssss","青冬栗");
        tbUserService.remove(wrapper);
    }

    @Test
    public void testUpdate(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        // 将sql 语句中众多的条件 换成了代码 这里没有细讲，之后会出文章 讲这个Wrapper。
        // 此处意思是  拼成 sql 语句即为  username=qqqq (注：是更在where子句后)
        wrapper.eq("id",1);
        User crush = new User().setUsername("宁在春").setPasswrod("123456");
        tbUserService.update(crush,wrapper);
    }

    @Test
    public void testDelete(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 将sql 语句中众多的条件 换成了代码 这里没有细讲，之后会出文章 讲这个Wrapper。
        // 此处意思是  拼成 sql 语句即为  username=qqqq (注：是更在where子句后)
        wrapper.eq("username","qqqq");
        boolean remove = tbUserService.remove(wrapper);
        System.out.println(remove);
    }

    @Test
    public void testPage(){
        // 第一个参数 当前页码 第二个参数是 每一页的大小
        // 这里的 1,5 说的是查询第一页 ,每页展示5条
        Page<User> page = new Page<>(1,5);
        Page<User> tbUserPage = tbUserService.page(page);
        // 传给前台时，并不需要这么读取，这里是为了展示  getRecords() 是获取查询到的记录。
        List<User> records = tbUserPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void  selectList(){
        List<User> list = tbUserService.list();
    }


    @Test
    public void testSelectCache(){
        List<User> list = tbUserService.list();
        list.forEach(System.out::println);
        System.out.println(list.get(0).getCreateTime());
    }

    /**
     * 事务回滚
     */
    @Transactional
    @Test
    public void testWork(){
        //start-------- delete from tb_user where username=宁在春;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username","宁在春");
        tbUserService.remove(wrapper);
        //end

        //start-------- update set username="我是新手" where id99999=1;
        UpdateWrapper<User> wrapper1 = new UpdateWrapper<>();
        // 在这里我故意将字段写错 那么这条SQL 语句 肯定会报错。
        wrapper1.eq("id99999",1);
        User crush = new User().setUsername("我是新手");
        tbUserService.update(crush,wrapper1);
        //end---------- 这条sql 语句 ，我们知道肯定是不会生效的，那么上面生效的是否回回滚呢？
    }



}
