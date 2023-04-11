package cn.qzwxixi.controller;


import cn.qzwxixi.entity.Dept;
import cn.qzwxixi.service.DeptFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class DeptController_Consumer {

    @Resource
    private DeptFeignService deptFeignService;

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return deptFeignService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return deptFeignService.list();
    }

    @RequestMapping(value = "/consumer/dept/feign/timeout")
    public String DeptFeignTimeout() {
        // openFeign-ribbon 客户端一般默认等待一秒钟，超过该时间就会报错
        return deptFeignService.DeptFeignTimeout();
    }


}
