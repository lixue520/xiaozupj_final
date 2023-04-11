package cn.edu.guet.weappdemo.service.impl;

import cn.edu.guet.weappdemo.bean.TranPay;
import cn.edu.guet.weappdemo.mapper.TranPayMapper;
import cn.edu.guet.weappdemo.service.TranPayMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/14 21:06
 */
@Service
public class TranPayMapperServiceImpl implements TranPayMapperService {

    @Autowired
    private TranPayMapper tranPayMapper;

    @Override
    public List<TranPay> FindAll() {
        System.out.println("dddddddd");
        System.out.println(tranPayMapper.findAll());
        return tranPayMapper.findAll();
    }

//    @Override
//    public Map<Object, Object> FindSome(Map<String,String> map) {   //查找服务
//
//        if(map.get("rqs")=="all"){
//            return tranPayMapper.findAll();
//        }else if(map.get("rqs")=="id"){
//            tranPayMapper.FindPayImgByUserID(Integer.parseInt(map.get("rqs")));
//        }else{
//            tranPayMapper.findOrderImg(map.get("rqs"));
//        }
//
//        return null;
//    }
}
