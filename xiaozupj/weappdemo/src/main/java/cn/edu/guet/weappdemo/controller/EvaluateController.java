package cn.edu.guet.weappdemo.controller;

import cn.edu.guet.weappdemo.http.HttpResult;
import cn.edu.guet.weappdemo.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评价控制器
 *
 * @author Liwei
 * @Date 2021-08-15 11:25
 */
@RestController
@RequestMapping("evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @GetMapping(value = "/findEvaluate")
    public HttpResult findEvaluate() {
        return HttpResult.ok(evaluateService.findEvaluate());
    }
    @GetMapping(value = "/find2Evaluate")
    public HttpResult find2Evaluate() {
        return HttpResult.ok(evaluateService.find2Evaluate());
    }

    @GetMapping(value = "/find1Evaluate")
    public HttpResult find1Evaluate(String user_name) {
        return HttpResult.ok(evaluateService.find1Evaluate(user_name));
    }


    @GetMapping(value = "/deleteEvaluate")
    public HttpResult deleteEvaluate(int id) {
        System.out.println("删除菜品");
        return HttpResult.ok(evaluateService.deleteEvaluate(id));
    }

    @GetMapping(value = "/insertEvaluate")
    public HttpResult insertEvaluate(int id,String user_name,String item_name,String time,String evaluate){
        System.out.println(id);
        System.out.println(user_name);
        System.out.println(item_name);
        System.out.println(time);
        System.out.println(evaluate);
        return HttpResult.ok(evaluateService.insertEvaluate(id,user_name,item_name,time,evaluate));
    }


}

