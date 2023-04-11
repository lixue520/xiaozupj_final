package com.crush.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.crush.excel.listener.DemoListener;
import com.crush.excel.mapper.DemoMapper;
import com.crush.excel.pojo.DemoModel;
import com.crush.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: crush
 * @Date: 2021-10-31 14:24
 * version 1.0
 */
@RestController
public class ExcelController {


    @Autowired
    ExcelService excelService;

    @PostMapping("/excel")
    public String upload(MultipartFile file,
                         HttpServletRequest request,
                         HttpServletResponse response){
        return excelService.upload(file, request, response);
    }

    @PostMapping("/import")
    public String importExcel(MultipartFile file,
                         HttpServletRequest request,
                         HttpServletResponse response){
        return excelService.importExcel(file, request, response);
    }

    @Autowired
    DemoMapper demoMapper;

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoModel.class, new DemoListener(demoMapper)).sheet().doRead();
        return "success";
    }


    @GetMapping("/down/{name}")
    public void down(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response){
       excelService.down(name,request,response);
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //这里的sheet的参数就是生成后的工作表的名称
        EasyExcel.write(response.getOutputStream(), DemoModel.class).sheet("模板").doWrite(data());
    }

    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @GetMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DemoModel.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    private List<DemoModel> data(){
        List<DemoModel> demoModels = new ArrayList<>();
        DemoModel model = new DemoModel();
        model.setName("宁在春");
        model.setCommunityName("知乎");
        model.setHomePageUrl("https://www.zhihu.com/creator/manage/creation/all");
        model.setEmail("nza_wyh@163.com");
        model.setSpecialty("SpringBoot");
        model.setStartDate("2021-10-31 12:00:00");
        DemoModel model2 = new DemoModel();
        model2.setName("宁在春2");
        model2.setCommunityName("知乎2");
        model2.setHomePageUrl("2https://www.zhihu.com/creator/manage/creation/all");
        model2.setEmail("nza_wyh@163.com2");
        model2.setSpecialty("SpringBoot2");
        model2.setStartDate("2021-10-31 12:00:00");
        demoModels.add(model);
        demoModels.add(model2);
        return demoModels;
    }
}
