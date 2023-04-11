package cn.qzwxixi.service;

import cn.qzwxixi.entity.Dept;

import java.util.List;

public interface DeptService {

    Dept get(Integer deptNo);

    List<Dept> selectAll();
}
