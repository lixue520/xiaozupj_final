package com.crush.mybatisplus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crush.mybatisplus.entity.TbUser;
import com.crush.mybatisplus.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
@RestController
@RequestMapping("/test")
public class TbUserController {


    private final ITbUserService tbUserService;

    public TbUserController(ITbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }


    @GetMapping("/list")
    public List<TbUser> getList(){
        return tbUserService.getList();
    }

    @GetMapping("/one/{id}")
    public TbUser getById(@PathVariable("id") String id){
        return tbUserService.getOneById(id);
    }

    @GetMapping("/username/{username}")
    public List<TbUser> getByUsername(@PathVariable("username") String username){
        return tbUserService.getByUsername(username);
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") String id){
        return tbUserService.deleteById(id);
    }


    @GetMapping("/update/{id}/{username}")
    public TbUser updateById(@PathVariable("id") String id,@PathVariable("username") String username){
        return tbUserService.updateById(id,username);
    }









}

